package com.atendimento.api.service.impl;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Departamento;
import com.atendimento.api.enums.Status;
import com.atendimento.api.exception.ValidaAssuntoException;
import com.atendimento.api.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    private static final String ASSUNTO_1 = "Problemas com cartão";
    private static final String ASSUNTO_2 = "Contratação de empréstimo";
    private static final String ASSUNTO_3 = "Outros Assuntos";
    private static final String ASSUNTO_NAO_ENCONTRADO = "Assunto não encontrado, por favor escolha um assunto válido para que possamos encaminhar para equipe responsável";

    @Override
    public AtendimentoResponse vericarDisponibilidade(AtendimentoRequest request) {
        Departamento departamento = validaAssunto(request);
        Atendente atendente = buscarAtendente(departamento);
        Status status;
        if(atendente == null){
            status = Status.AGUARDANDO;
        }else{
            status = Status.EM_PROCESSAMENTO;
        }
        return AtendimentoResponse.builder()
                .numeroChamado(UUID.randomUUID())
                .assunto(request.getAssunto())
                .descricao(request.getDescricao())
                .atendente(atendente != null ? atendente.getNomeAtendente() : null)
                .status(status)
                .build();
    }

    private Atendente buscarAtendente(Departamento departamento) {
        List<Atendente> atendentes = Atendente.buscarAtendentePorDepartamento(departamento);
        for(Atendente atendente: atendentes){
            int quantidadeChamado = atendente.getQuantidadeChamado();
            if(quantidadeChamado < 3){
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
