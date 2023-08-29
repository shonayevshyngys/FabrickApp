package com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransferAmountDTO {
    private double debtorAmount;
    private String debtorCurrency;
    private double creditorAmount;
    private String creditorCurrency;
    private LocalDate creditorCurrencyDate;
    private double exchangeRate;
}
