package com.atendimento.api.controller;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.service.AtendimentoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AtendimentoController {

    private AtendimentoService atendimentoService;

    @PostMapping("/atendimentos")
    public ResponseEntity solicitar(@RequestBody @Valid AtendimentoRequest request) {

        var response = atendimentoService.vericarDisponibilidade(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/atendimentos/{numeroChamado}/finaliza")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity finalizarChamado(@PathVariable @NotEmpty String numeroChamado) {

        var response = atendimentoService.finalizar(numeroChamado);

        return ResponseEntity.ok(response);
    }
}
