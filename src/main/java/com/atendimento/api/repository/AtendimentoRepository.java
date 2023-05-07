package com.atendimento.api.repository;

import com.atendimento.api.entity.AtendimentoEntity;
import com.atendimento.api.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Long> {

    Optional<AtendimentoEntity> findByNumeroChamado(@Param("numeroChamado") String numeroChamado);

    List<AtendimentoEntity> findByStatusAndAssunto(@Param("status") Status status, @Param("assunto") String assunto);

}
