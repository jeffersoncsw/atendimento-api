package com.atendimento.api.service;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;

public interface AtendimentoService {

    AtendimentoResponse vericarDisponibilidade(AtendimentoRequest request);

    AtendimentoResponse finalizar(String numeroChamado);
}
