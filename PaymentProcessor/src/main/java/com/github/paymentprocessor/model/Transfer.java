package com.github.paymentprocessor.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
