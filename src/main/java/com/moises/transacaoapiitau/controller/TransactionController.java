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
    @Operation(description = "Endpoint respons�vel para adicionar transa��es")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transa��o gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisi��o"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> saveTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {

        transactionService.addTransaction(transactionRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint respons�vel por deletar transa��es")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transa��o deletada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Data e horas maior que a atual"),
            @ApiResponse(responseCode = "422", description = "Campos n�o atendem aos requisitos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> removeAllTransactions() {

        transactionService.removeAllTransactions();

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
