package com.atendimento.api.service.impl;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import com.atendimento.api.entity.AtendimentoEntity;
import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Departamento;
import com.atendimento.api.enums.Status;
import com.atendimento.api.exception.ChamadoNaoEncontradoException;
import com.atendimento.api.exception.ValidaAssuntoException;
import com.atendimento.api.mapper.AtendimentoMapper;
import com.atendimento.api.repository.AtendimentoRepository;
import com.atendimento.api.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    private static final String ASSUNTO_1 = "Problemas com cartão";
    private static final String ASSUNTO_2 = "Contratação de empréstimo";
    private static final String ASSUNTO_3 = "Outros Assuntos";
    private static final String ASSUNTO_NAO_ENCONTRADO = "Assunto não encontrado, por favor escolha um assunto válido para que possamos encaminhar para equipe responsável";

    private final AtendimentoRepository atendimentoRepository;
    private final AtendimentoMapper atendimentoMapper;

    @Override
    public AtendimentoResponse vericarDisponibilidade(AtendimentoRequest request) {
        Departamento departamento = validaAssunto(request);
        Atendente atendente = buscarAtendente(departamento);
        Status status;
        if (atendente == null) {
            status = Status.AGUARDANDO;
        } else {
            status = Status.EM_PROCESSAMENTO;
        }
        var entity = AtendimentoEntity.builder()
                .numeroChamado(UUID.randomUUID().toString())
                .assunto(request.getAssunto())
                .descricao(request.getDescricao())
                .atendente(atendente)
                .status(status)
                .aberturaChamado(OffsetDateTime.now())
                .build();

        atendimentoRepository.save(entity);

        return atendimentoMapper.toAtendimentoResponse(entity);
    }

    @Override
    public AtendimentoResponse finalizar(String numeroChamado) {
        var entity = atendimentoRepository.findByNumeroChamado(numeroChamado).orElseThrow(() ->
                new ChamadoNaoEncontradoException("Numero de chamado não encontrado."));

        entity.finaliza();

        atendimentoRepository.saveAndFlush(entity);

        Atendente.liberarAtendimento(entity.getAtendente());

        verificarChamadoAguardando(entity.getAtendente(), entity.getAssunto());

        return atendimentoMapper.toAtendimentoResponse(entity);
    }

    private void verificarChamadoAguardando(Atendente atendente, String assunto) {
        var result = atendimentoRepository.findByStatusAndAssunto(Status.AGUARDANDO, assunto);
        if (!result.isEmpty()) {
            result.get(0).aguardando(atendente);
            Atendente.atribuirAtendimento(atendente);
            atendimentoRepository.saveAndFlush(result.get(0));
        }
    }

    private Atendente buscarAtendente(Departamento departamento) {
        List<Atendente> atendentes = Atendente.buscarAtendentePorDepartamento(departamento);
        Collections.shuffle(atendentes);
        if (!atendentes.isEmpty()) {
            for (Atendente atendente : atendentes) {
                int quantidadeChamado = atendente.getQuantidadeChamado();
                atendente.setQuantidadeChamado(quantidadeChamado + 1);
                return atendente;
            }
        }
        return null;
    }

    private Departamento validaAssunto(AtendimentoRequest request) {
        String verifica = request.getAssunto();
        if (verifica.equals(ASSUNTO_1)) {
            return Departamento.CARTAO;
        } else if (verifica.equals(ASSUNTO_2)) {
            return Departamento.EMPRESTIMO;
        } else if (verifica.equals(ASSUNTO_3)) {
            return Departamento.OUTROS_ASSUNTOS;
        } else {
            throw new ValidaAssuntoException(ASSUNTO_NAO_ENCONTRADO);
        }
    }
}
