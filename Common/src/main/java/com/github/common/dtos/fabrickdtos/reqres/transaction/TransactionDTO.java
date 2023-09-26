package com.github.common.dtos.fabrickdtos.reqres.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO implements Serializable {
    private long transactionId;
    private long operationId;
    private LocalDate accountingDate;
    private LocalDate valueDate;
    private TypeDTO type;
    private double amount;
    private String currency;
    private String description;
}
