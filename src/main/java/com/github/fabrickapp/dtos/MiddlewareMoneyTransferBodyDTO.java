package com.github.fabrickapp.dtos;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class MiddlewareMoneyTransferBodyDTO {
    @NonNull
    private String receiverName;

    @NonNull @Size(min = 8, max = 30, message = "Insert either swift or iban")
    private String accountCode;

    @NonNull @Size(min = 2, max = 140, message = "description should be at max 140 characters")
    private String description;

    @NonNull @Size(min = 3, max = 3, message = "Currency should be 3 letters")
    private String currency;

    @DecimalMin(value = "0.0", message = "amount should be more than 0")
    private Double amount;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate executionDate;
}
