package com.atendimento.api.mapper;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import com.atendimento.api.entity.AtendimentoEntity;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoMapper {

    public AtendimentoEntity toAtendimentoEntity(AtendimentoRequest request) {
        return AtendimentoEntity.builder()
                .assunto(request.getAssunto())
                .descricao(request.getDescricao())
                .build();
    }

    public AtendimentoResponse toAtendimentoResponse(AtendimentoEntity entity) {
        return AtendimentoResponse.builder()
                .numeroChamado(entity.getNumeroChamado())
                .assunto(entity.getAssunto())
                .descricao(entity.getDescricao())
                .atendente(entity.getAtendente() != null ? entity.getAtendente().getNomeAtendente() : null)
                .status(entity.getStatus())
                .aberturaChamado(entity.getAberturaChamado())
                .finalizacaoChamado(entity.getFinalizacaoChamado())
                .build();
    }
}
