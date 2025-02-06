package com.api_itau.transacaoApi.business.services;

import com.api_itau.transacaoApi.controller.dtos.TransacaoRequestDTO;
import com.api_itau.transacaoApi.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {

        log.info("Iniciado o processamento de cálculo de transacoes. " + dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora da transação maiores que a data e hora atual.");
            throw new UnprocessableEntity("Data e hora da transação maiores que a data e hora atual.");
        }
        if (dto.valor() < 0) {
            log.error("O valor da transação não pode ser menor que zero.");
            throw new UnprocessableEntity("O valor da transação não pode ser menor que zero.");
        }

        listaTransacoes.add(dto);
        log.info("Transaçoes adicionadas com sucesso.");
    }

    public void limparTransacoes() {
        log.info("Iniciado processamento para limpar transações.");
        listaTransacoes.clear();
        log.info("Transaçoes limpas com sucesso.");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca) {
        log.info("Iniciado processamento para buscar transações. " + intervaloBusca + " segundos.");
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transacoes com sucesso");
        return listaTransacoes.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();

    }

}
