package com.atendimento.api.controller.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoRequest {

    @NotEmpty
    private String assunto;

    @NotEmpty
    private String descricao;

}
