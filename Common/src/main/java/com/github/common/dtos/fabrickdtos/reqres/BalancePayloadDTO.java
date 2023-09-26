package com.github.common.dtos.fabrickdtos.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalancePayloadDTO implements Serializable {
    private LocalDate date;
    private double balance;
    private double availableBalance;
    private String currency;
}
