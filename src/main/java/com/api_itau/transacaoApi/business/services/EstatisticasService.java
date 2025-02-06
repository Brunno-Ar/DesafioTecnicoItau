package com.api_itau.transacaoApi.business.services;

import com.api_itau.transacaoApi.controller.dtos.EstatisticasResposeDTO;
import com.api_itau.transacaoApi.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResposeDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {
        log.info("Iniciado o processamento de cálculo de estatísticas. Pelo periodo de tempo de " + intervaloBusca + " segundos.");

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if(transacoes.isEmpty()) {
            return new EstatisticasResposeDTO(0L, 0D, 0D, 0D, 0D);
        }

        DoubleSummaryStatistics estatisticasTransacoes =
                transacoes.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornada com sucesso.");

        return new EstatisticasResposeDTO(estatisticasTransacoes.getCount(), estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(), estatisticasTransacoes.getMin(), estatisticasTransacoes.getMax());
    }


}
