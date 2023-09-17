package com.github.service.validators;

import com.github.common.dtos.MiddlewareMoneyTransferBodyDTO;
import org.springframework.stereotype.Component;

@Component
public class TransferValidator {
    public boolean validateMiddlewareDTO(MiddlewareMoneyTransferBodyDTO dto)
    {
        return validateIbanSwift(dto.getAccountCode()) && validateCurrency(dto.getCurrency());
    }

    public boolean validateIbanSwift(String code)
    {
        return code.matches("^([A-Z]{2})(\\d{2})([A-Z\\d]+)|([a-zA-Z]){4}([a-zA-Z]){2}([0-9a-zA-Z]){2}([0-9a-zA-Z]{3})?");
    }

    public boolean validateCurrency(String curr)
    {
        return curr.toUpperCase().equals(curr) && curr.length() == 3;
    }
}
