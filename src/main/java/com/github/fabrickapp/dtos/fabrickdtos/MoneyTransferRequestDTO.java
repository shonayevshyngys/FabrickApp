package com.github.fabrickapp.dtos.fabrickdtos;

import com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer.TransferCreditorDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MoneyTransferRequestDTO {
    private TransferCreditorDTO creditor; //required
    private LocalDate executionDate; //required if inInstant if false
    private String uri;
    private String description; //required max140 chars
    private double amount;
    private String currency; //required
    private boolean isUrgent;
    private boolean isInstant;
    private String feeType;
    private String feeAccountId;
}
