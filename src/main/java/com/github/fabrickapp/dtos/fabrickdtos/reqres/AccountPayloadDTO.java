package com.github.fabrickapp.dtos.fabrickdtos.reqres;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountPayloadDTO {
    private long accountId;
    private String iban;
    private String cabCode;
    private String countryCode;
    private String internationalCin;
    private String nationalCin;
    private String alias;
    private String productName;
    private String holderName;
    private LocalDate activatedDate;
    private String currency;

}
