package com.atendimento.api.enums;

import java.util.ArrayList;
import java.util.List;

public enum Atendente {

    ATENDENTE_1("Maria", Departamento.CARTAO, 0),
    ATENDENTE_2("João", Departamento.CARTAO, 0),
    ATENDENTE_3("Isabela", Departamento.CARTAO, 0),
    ATENDENTE_4("José", Departamento.EMPRESTIMO, 0),
    ATENDENTE_5("Karina", Departamento.EMPRESTIMO, 0),
    ATENDENTE_6("Carlos", Departamento.EMPRESTIMO, 0),
    ATENDENTE_7("César", Departamento.OUTROS_ASSUNTOS, 0),
    ATENDENTE_8("Joana", Departamento.OUTROS_ASSUNTOS, 0),
    ATENDENTE_9("Rafaela", Departamento.OUTROS_ASSUNTOS, 0);

    private String nomeAtendente;
    private Departamento departamento;
    private int quantidadeChamado;

    Atendente(String nomeAtendente, Departamento departamento, int quantidadeChamado) {
        this.nomeAtendente = nomeAtendente;
        this.departamento = departamento;
        this.quantidadeChamado = quantidadeChamado;
    }

    public String getNomeAtendente() {
        return nomeAtendente;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public int getQuantidadeChamado() {
        return quantidadeChamado;
    }

    public void setQuantidadeChamado(int quantidadeChamado) {
        this.quantidadeChamado = quantidadeChamado;
    }

    public static List<Atendente> buscarAtendentePorDepartamento(Departamento departamento){
        List<Atendente> result = new ArrayList<>();
        for(Atendente atendente: values()){
            if(atendente.getDepartamento().equals(departamento)){
                result.add(atendente);
            }
        }
        return result;
    }
}
