package com.github.fabrickapp.dtos.fabrickdtos.reqres;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BalancePayloadDTO {
    private LocalDate date;
    private double balance;
    private double availableBalance;
    private String currency;
}
