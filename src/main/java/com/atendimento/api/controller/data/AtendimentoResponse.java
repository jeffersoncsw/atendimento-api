package com.atendimento.api.controller.data;

import com.atendimento.api.enums.Status;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AtendimentoResponse {

    private String numeroChamado;
    private String assunto;
    private String descricao;
    private String atendente;
    private Status status;
    private OffsetDateTime aberturaChamado;
    private OffsetDateTime finalizacaoChamado;

}
