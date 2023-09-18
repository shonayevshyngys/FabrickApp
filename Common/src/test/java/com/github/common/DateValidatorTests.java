package com.github.common;

import com.github.common.validators.DateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DateValidatorTests {

    DateValidator validator = new DateValidator();
    @Test
    void secondDateLessTest()
    {
        LocalDate from = LocalDate.now().minusMonths(2);
        LocalDate to = LocalDate.now().minusMonths(3);
        var res = validator.validateDates(from, to);
        Assertions.assertFalse(res);
    }

    @Test
    void datesInTheFutureTest()
    {
        Assertions.assertAll(
                () -> {
                    LocalDate from = LocalDate.now().plusMonths(2);
                    LocalDate to = LocalDate.now().plusMonths(3);
                    Assertions.assertFalse(validator.validateDates(from, to));
                },
                () -> {
                    LocalDate from = LocalDate.now().minusMonths(2);
                    LocalDate to = LocalDate.now().plusMonths(3);
                    Assertions.assertFalse(validator.validateDates(from, to));
                },
                () -> {
                    LocalDate from = LocalDate.now().plusMonths(2);
                    LocalDate to = LocalDate.now();
                    Assertions.assertFalse(validator.validateDates(from, to));
                },
                () -> {
                    Assertions.assertTrue(validator.validateExecutionDates(LocalDate.now()));
                    Assertions.assertTrue(validator.validateExecutionDates(LocalDate.now().plusMonths(2)));
                }
        );
    }
}
