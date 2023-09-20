package com.github.fabrickpaymentquery.model;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Transfer {

    private long id;
    private String uuid;

    private String status;
    private String errorCode;
    private String errorDesc;

    private LocalDate date;
    private long transferId;
    private String transferCode;
    private String transferStatus;
    private String transferDirection;
    private String transferCurrency;
    private double transferAmount;
    private String beneficiaryName;

}
