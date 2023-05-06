package com.atendimento.api.service;

import com.atendimento.api.controller.data.AtendimentoRequest;

public interface AtendimentoService {

    boolean validaAssunto(AtendimentoRequest request);
}
