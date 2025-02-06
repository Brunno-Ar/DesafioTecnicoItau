package com.api_itau.transacaoApi.controller;

import com.api_itau.transacaoApi.business.services.EstatisticasService;
import com.api_itau.transacaoApi.controller.dtos.EstatisticasResposeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticasController {

    public final EstatisticasService estatisticasService;

    public ResponseEntity<EstatisticasResposeDTO> buscarEstatisticas(@RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
        return ResponseEntity.ok(estatisticasService.calcularEstatisticasTransacoes(intervaloBusca));
    }

}
