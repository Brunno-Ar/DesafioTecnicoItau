package com.api_itau.transacaoApi.controller;

import com.api_itau.transacaoApi.business.services.EstatisticasService;
import com.api_itau.transacaoApi.controller.dtos.EstatisticasResposeDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    public final EstatisticasService estatisticasService;
    @GetMapping
    @Operation(description = "Endpoint para buscar estatisticas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca de estatisticas efetuada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatisticas."),
            @ApiResponse(responseCode = "500", description = "Erro interno.")})
    public ResponseEntity<EstatisticasResposeDTO> buscarEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }

}
