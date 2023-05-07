package com.atendimento.api.controller;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.service.AtendimentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AtendimentoController {

    private AtendimentoService atendimentoService;

    @PostMapping("/atendimentos")
    public ResponseEntity solicitar(@RequestBody @Valid AtendimentoRequest request){

        var response = atendimentoService.vericarDisponibilidade(request);

        return new ResponseEntity(response, HttpStatus.ACCEPTED);
    }
}
