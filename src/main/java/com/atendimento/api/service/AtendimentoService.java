package com.atendimento.api.service;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AtendimentoService {

    AtendimentoResponse vericarDisponibilidade(AtendimentoRequest request);

    AtendimentoResponse finalizar(String numeroChamado);

    Page<AtendimentoResponse> obterTodosAtendimentos(Pageable page);
}
