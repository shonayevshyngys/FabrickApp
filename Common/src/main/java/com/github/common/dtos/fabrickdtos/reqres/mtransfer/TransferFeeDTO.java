package com.github.common.dtos.fabrickdtos.reqres.mtransfer;

import lombok.Data;

@Data
public class TransferFeeDTO {
    private String feeCode;
    private String description;
    private double amount;
    private String currency;
}
