package com.api_itau.transacaoApi.controller.dtos;

import com.api_itau.transacaoApi.business.services.TransacaoService;

import java.util.List;

public record EstatisticasResposeDTO(Long cont, Double sum, Double avg, Double min, Double max) {

}
