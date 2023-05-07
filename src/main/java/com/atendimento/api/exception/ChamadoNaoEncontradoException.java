package com.atendimento.api.exception;

public class ChamadoNaoEncontradoException extends RuntimeException {
    public ChamadoNaoEncontradoException(String msg) {
        super(msg);
    }
}
