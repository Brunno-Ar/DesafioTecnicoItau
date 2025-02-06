package com.api_itau.transacaoApi.controller;

import com.api_itau.transacaoApi.business.services.TransacaoService;
import com.api_itau.transacaoApi.controller.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    @Operation(description = "Endpoint para adiciona uma nova transação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação adicionada com sucesso."),
            @ApiResponse(responseCode = "422", description = "Data e hora da transação maiores que a data e hora atual."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição."),
            @ApiResponse(responseCode = "500", description = "Erro interno.")})
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto) {
        transacaoService.adicionarTransacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint para deletar uma nova transação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação excluida com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de requisição."),
            @ApiResponse(responseCode = "500", description = "Erro interno.")})
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }

}
