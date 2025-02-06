package com.api_itau.transacaoApi.business.services;

import com.api_itau.transacaoApi.controller.dtos.TransacaoRequestDTO;
import com.api_itau.transacaoApi.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto) {
        if(dto.dataHora().isAfter(OffsetDateTime.now())) {
            throw new UnprocessableEntity("Data e hora da transação maiores que a data e hora atual.");
        }
    }
}
