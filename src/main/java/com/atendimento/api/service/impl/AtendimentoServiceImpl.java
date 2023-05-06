package com.atendimento.api.service.impl;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.exception.ValidaAssuntoException;
import com.atendimento.api.service.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AtendimentoServiceImpl implements AtendimentoService {

    private static final String ASSUNTO_1 = "Problemas com cartão";
    private static final String ASSUNTO_2 = "Contratação de empréstimo";
    private static final String ASSUNTO_3 = "Outros Assuntos";
    private static final String ASSUNTO_NAO_ENCONTRADO = "Assunto não encontrado, por favor escolha um assunto válido para que possamos encaminhar para equipe responsável";

    @Override
    public boolean validaAssunto(AtendimentoRequest request) {
        String verifica = request.getAssunto();
        if(verifica.equals(ASSUNTO_1) || verifica.equals(ASSUNTO_2) || verifica.equals(ASSUNTO_3)){
            return true;
        }else{
            throw new ValidaAssuntoException(ASSUNTO_NAO_ENCONTRADO);
        }
    }
}
