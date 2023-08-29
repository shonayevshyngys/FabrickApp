package com.github.fabrickapp.dtos.fabrickdtos.reqres.transaction;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionDTO {
    private long transactionId;
    private long operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;
    private TypeDTO type;
    private double amount;
    private String currency;
    private String description;
}
