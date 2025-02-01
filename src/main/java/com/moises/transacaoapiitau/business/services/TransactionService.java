package com.moises.transacaoapiitau.business.services;

import com.moises.transacaoapiitau.controller.dtos.TransactionRequestDTO;
import com.moises.transacaoapiitau.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final List<TransactionRequestDTO> transactionList = new ArrayList<>();

    public void addTransaction(TransactionRequestDTO transactionRequestDTO) {

        log.info("Iniciado processo de gravar transações");

        validadeTransactionRequestData(transactionRequestDTO);

        log.info("Finalizando processo de gravar transações");

        transactionList.add(transactionRequestDTO);
    }

    private static void validadeTransactionRequestData(TransactionRequestDTO transactionRequestDTO) {

        if (transactionRequestDTO.dateTime().isAfter(OffsetDateTime.now())) {
            log.error("Data e horas maior que a atual");
            throw new UnprocessableEntity("Data e horas maior que a atual");
        }

        if (transactionRequestDTO.valor() < 0) {
            log.error("Valor não pode ser menor que zero");
            throw new UnprocessableEntity("Valor não pode ser menor que zero");
        }

    }

    public void removeAllTransactions() {

        log.info("Deletando transações...");

        transactionList.clear();
    }

    public List<TransactionRequestDTO> findAllTransactions(Integer periodInterval) {

        log.info("Buscando transações...");

        OffsetDateTime dateTimeInterval = OffsetDateTime.now().minusSeconds(periodInterval);

        return transactionList.stream()
                .filter(transaction -> transaction.dateTime().isAfter(dateTimeInterval))
                .toList();

    }


}
