package com.atendimento.api.enums;

public enum Atendente {

    ATENDENTE_1("Maria", Departamento.CARTAO),
    ATENDENTE_2("João", Departamento.CARTAO),
    ATENDENTE_3("Isabela", Departamento.CARTAO),
    ATENDENTE_4("José", Departamento.EMPRESTIMO),
    ATENDENTE_5("Karina", Departamento.EMPRESTIMO),
    ATENDENTE_6("Carlos", Departamento.EMPRESTIMO),
    ATENDENTE_7("César", Departamento.OUTROS_ASSUNTOS),
    ATENDENTE_8("Joana", Departamento.OUTROS_ASSUNTOS),
    ATENDENTE_9("Rafaela", Departamento.OUTROS_ASSUNTOS);

    private String nomeAtendente;
    private Departamento departamento;

    Atendente(String nomeAtendente, Departamento departamento) {
        this.nomeAtendente = nomeAtendente;
        this.departamento = departamento;
    }
}
