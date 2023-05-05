package com.atendimento.api.controller.data;

import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponse {

    @Id
    private UUID numeroChamado;

    @NotNull
    @NotEmpty
    private String assunto;

    @NotNull
    @NotEmpty
    private String descricao;

    @Enumerated
    private Atendente atendente;

    @Enumerated
    private Status status;

}
