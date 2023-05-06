package com.atendimento.api.controller;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Status;
import com.atendimento.api.service.AtendimentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AtendimentoController {

    private AtendimentoService atendimentoService;

    @PostMapping("/atendimentos")
    public ResponseEntity solicitar(@RequestBody AtendimentoRequest request){

        atendimentoService.validaAssunto(request);

        var response = AtendimentoResponse
                .builder()
                .numeroChamado(UUID.randomUUID())
                .assunto(request.getAssunto())
                .descricao(request.getDescricao())
                .atendente(Atendente.ATENDENTE_1)
                .status(Status.EM_PROCESSAMENTO)
                .build();
        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
