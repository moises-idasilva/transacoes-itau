package com.moises.transacaoapiitau.controller;

import com.moises.transacaoapiitau.business.services.TransactionService;
import com.moises.transacaoapiitau.controller.dtos.TransactionRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    @Operation(description = "Endpoint responsável para adicionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> saveTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        transactionService.addTransaction(transactionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Data e horas maior que a atual"),
            @ApiResponse(responseCode = "422", description = "Campos não atendem aos requisitos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> removeAllTransactions() {

        transactionService.removeAllTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
