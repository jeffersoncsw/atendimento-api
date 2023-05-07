package com.atendimento.api.entity;

import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Status;
import com.atendimento.api.exception.ValidaAssuntoException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "atendimento")
public class AtendimentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_chamado_atendimento", nullable = false)
    private String numeroChamado;

    @Column(name = "assunto_atendimento", nullable = false)
    private String assunto;

    @Column(name = "descricao_atendimento", nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "atendente_atendimento")
    private Atendente atendente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_atendimento", nullable = false)
    private Status status;

    @Column(name = "abertura_chamado_atendimento", nullable = false)
    private OffsetDateTime aberturaChamado;

    @Column(name = "finalizacao_chamado_atendimento")
    private OffsetDateTime finalizacaoChamado;

    public void finaliza() {
        if (!podeFinalizar()) {
            throw new ValidaAssuntoException("Não pode finalizar");
        }
        setStatus(Status.FINALIZADO);
        setFinalizacaoChamado(OffsetDateTime.now());
    }

    public void aguardando(Atendente atendente) {
        setAtendente(atendente);
        setStatus(Status.EM_PROCESSAMENTO);
    }

    private boolean podeFinalizar() {
        return Status.EM_PROCESSAMENTO.equals(getStatus());
    }

}
