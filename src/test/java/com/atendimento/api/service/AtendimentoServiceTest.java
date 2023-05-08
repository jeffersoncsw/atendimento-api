package com.atendimento.api.service;

import com.atendimento.api.controller.data.AtendimentoRequest;
import com.atendimento.api.controller.data.AtendimentoResponse;
import com.atendimento.api.entity.AtendimentoEntity;
import com.atendimento.api.enums.Atendente;
import com.atendimento.api.enums.Status;
import com.atendimento.api.mapper.AtendimentoMapper;
import com.atendimento.api.repository.AtendimentoRepository;
import com.atendimento.api.service.impl.AtendimentoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class AtendimentoServiceTest {

    private static final String ASSUNTO_1 = "Problemas com cartão";
    private static final String ASSUNTO_2 = "Contratação de empréstimo";
    private static final String ASSUNTO_3 = "Outros Assuntos";
    private static final String ASSUNTO_NAO_ENCONTRADO = "Assunto não encontrado, por favor escolha um assunto válido para que possamos encaminhar para equipe responsável";

    @InjectMocks
    private AtendimentoServiceImpl atendimentoService;

    @Mock
    private AtendimentoRepository atendimentoRepository;

    @Mock
    private AtendimentoMapper atendimentoMapper;

    private AtendimentoRequest request;

    private AtendimentoResponse response;

    private AtendimentoEntity entity;

    @BeforeEach
    public void setUp(){
        request = criarRequest();
        response = criarResponse();
        entity = criarEntity();
    }

    @Test
    public void deveCriarAtendimentoComSucesso(){

        try {

            when(atendimentoRepository.save(any())).thenReturn(entity);
            when(atendimentoMapper.toAtendimentoResponse(any())).thenReturn(response);

            var response = atendimentoService.vericarDisponibilidade(request);

            verify(atendimentoRepository).save(any());
            verify(atendimentoMapper).toAtendimentoResponse(any());

            assertThat(response).isNotNull();
            assertThat(response.getNumeroChamado()).isEqualTo(response.getNumeroChamado());
            assertThat(response.getAssunto()).isEqualTo(response.getAssunto());
            assertThat(response.getDescricao()).isEqualTo(response.getDescricao());
            assertThat(response.getAtendente()).isEqualTo(response.getAtendente());
            assertThat(response.getStatus()).isEqualTo(response.getStatus());
            assertThat(response.getAberturaChamado()).isEqualTo(response.getAberturaChamado());
        } catch (Exception ex){
            fail("Não deve cair aqui.");
        }
    }

    @Test
    public void naoDeveCriarUmAtendimento(){

        AtendimentoRequest request = AtendimentoRequest.builder().assunto("PIX").descricao("Falar sobre Pix").build();

        try {
            when(atendimentoRepository.save(any())).thenReturn(null);
            atendimentoService.vericarDisponibilidade(request);
            fail("Não deve cair aqui.");
        }catch (Exception ex){
            assertThat(ex.getMessage()).isEqualToIgnoringCase(ASSUNTO_NAO_ENCONTRADO);
        }
    }

    @Test
    public void deveVerificarAssuntoAtendimentoComoCartoes(){

        AtendimentoRequest request = AtendimentoRequest.builder().assunto("Problemas com cartão").build();

        try {
            atendimentoService.vericarDisponibilidade(request);
            assertThat(request.getAssunto()).isEqualTo(ASSUNTO_1);
        }catch (Exception ex){
            assertThat(ex.getMessage()).isEqualToIgnoringCase(ASSUNTO_NAO_ENCONTRADO);
            fail("Não deve cair aqui.");
        }
    }

    @Test
    public void deveVerificarAssuntoAtendimentoComoEmprestimo(){

        AtendimentoRequest request = AtendimentoRequest.builder().assunto("Contratação de empréstimo").build();

        try {
            atendimentoService.vericarDisponibilidade(request);
            assertThat(request.getAssunto()).isEqualTo(ASSUNTO_2);
        }catch (Exception ex){
            assertThat(ex.getMessage()).isEqualToIgnoringCase(ASSUNTO_NAO_ENCONTRADO);
            fail("Não deve cair aqui.");
        }
    }

    @Test
    public void deveVerificarAssuntoAtendimentoComoOutrosAssuntos(){

        AtendimentoRequest request = AtendimentoRequest.builder().assunto("Outros Assuntos").build();

        try {
            atendimentoService.vericarDisponibilidade(request);
            assertThat(request.getAssunto()).isEqualTo(ASSUNTO_3);
        }catch (Exception ex){
            assertThat(ex.getMessage()).isEqualToIgnoringCase(ASSUNTO_NAO_ENCONTRADO);
            fail("Não deve cair aqui.");
        }
    }

    private AtendimentoRequest criarRequest() {
        return AtendimentoRequest.builder()
                .assunto("Problemas com cartão")
                .descricao("Problema relacionado com cartão")
                .build();
    }

    private AtendimentoResponse criarResponse() {
        return AtendimentoResponse.builder()
                .numeroChamado("123456789")
                .assunto("Problemas com cartão")
                .descricao("Problema relacionado com cartão")
                .atendente(Atendente.ATENDENTE_1.getNomeAtendente())
                .status(Status.EM_PROCESSAMENTO)
                .aberturaChamado(OffsetDateTime.now())
                .build();
    }

    private AtendimentoEntity criarEntity() {
        return AtendimentoEntity.builder()
                .id(100L)
                .numeroChamado("123456789")
                .assunto("Problemas com cartão")
                .descricao("Problema relacionado com cartão")
                .atendente(Atendente.ATENDENTE_1)
                .status(Status.EM_PROCESSAMENTO)
                .aberturaChamado(OffsetDateTime.now())
                .build();
    }

}