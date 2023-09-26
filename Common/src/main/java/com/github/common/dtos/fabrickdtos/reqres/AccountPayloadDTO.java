package com.github.common.dtos.fabrickdtos.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountPayloadDTO implements Serializable {
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
