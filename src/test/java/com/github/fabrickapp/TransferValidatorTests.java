package com.github.fabrickapp;

import com.github.fabrickapp.service.TransferValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransferValidatorTests {

    TransferValidator validator = new TransferValidator();
    @Test
    void validateIbanOrSwiftTest()
    {
        Assertions.assertAll(
                () -> {
                    String iban = "IT23A0336844430152923804660";
                    Assertions.assertTrue(validator.validateIbanSwift(iban));
                },
                () -> {
                    String swift = "UNCRITMMXXX";
                    Assertions.assertTrue(validator.validateIbanSwift(swift));
                },
                () -> {
                    String iban = "I0123";
                    Assertions.assertFalse(validator.validateIbanSwift(iban));
                }
        );
    }

    @Test
    void validateCurrency()
    {
        Assertions.assertAll(
                () -> {
                    String cur = "EUR";
                    Assertions.assertTrue(validator.validateCurrency(cur));
                },
                () -> {
                    String cur = "EuR";
                    Assertions.assertFalse(validator.validateCurrency(cur));
                },
                () -> {
                    String cur = "RUBLE";
                    Assertions.assertFalse(validator.validateCurrency(cur));
                }
        );
    }

}
