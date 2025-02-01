package com.moises.transacaoapiitau.business.services;

import com.moises.transacaoapiitau.controller.dtos.StatisticResponseDTO;
import com.moises.transacaoapiitau.controller.dtos.TransactionRequestDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StatisticService {

    public final TransactionService transactionService;

    public StatisticResponseDTO calculateTransactionsStatistics(Integer periodInterval) {

        List<TransactionRequestDTO> transactions = transactionService.findAllTransactions(periodInterval);

        if (transactions.isEmpty()) {
            return new StatisticResponseDTO(0, 0d, 0d, 0d, 0d);
        }

        log.info("Calculando estatísticas das transações");

        DoubleSummaryStatistics transactionsStatistics =
                transactions
                .stream()
                .mapToDouble(TransactionRequestDTO::valor)
                        .summaryStatistics();

        return new StatisticResponseDTO(transactionsStatistics.getCount(),
                                        transactionsStatistics.getSum(),
                                        transactionsStatistics.getAverage(),
                                        transactionsStatistics.getMin(),
                                        transactionsStatistics.getMax());
    }

}
