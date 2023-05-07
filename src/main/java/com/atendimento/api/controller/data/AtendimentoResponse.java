package com.atendimento.api.controller.data;

import com.atendimento.api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponse {

    private UUID numeroChamado;
    private String assunto;
    private String descricao;
    private String atendente;
    private Status status;

}
