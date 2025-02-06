package com.api_itau.transacaoApi.business.services;

import com.api_itau.transacaoApi.controller.dtos.EstatisticasResposeDTO;
import com.api_itau.transacaoApi.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResposeDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {
        List<TransacaoRequestDTO> transacoes =
                transacaoService.buscarTransacoes(intervaloBusca);

        DoubleSummaryStatistics estatisticasTransacoes =
                transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        return new EstatisticasResposeDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }


}
