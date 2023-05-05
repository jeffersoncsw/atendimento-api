package com.atendimento.api.controller;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class AtendimentoController {

    @PostMapping("/atendimentos")
    public ResponseEntity solicitar(@RequestBody AtendimentoRequest request){
        var response = AtendimentoResponse
                .builder()
                .numeroChamado(UUID.randomUUID())
                .assunto("Problemas com cartão")
                .descricao("Não estou conseguindo pegar minha fatura")
                .atendente(Atendente.ATENDENTE_1)
                .status(Status.EM_PROCESSAMENTO)
                .build();
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
