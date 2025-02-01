package com.moises.transacaoapiitau.controller.dtos;

import java.time.OffsetDateTime;

public record TransactionRequestDTO(Double valor, OffsetDateTime dateTime) {
}
