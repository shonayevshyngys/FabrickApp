package com.github.service.validators;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateValidator {
    public boolean validateDates(LocalDate from, LocalDate to)
    {
        boolean fromBeforeTo = from.isBefore(to);
        boolean areTheyInFuture = from.isBefore(LocalDate.now()) && (to.isBefore(LocalDate.now()) || to.isEqual(LocalDate.now()));
        return fromBeforeTo && areTheyInFuture;
    }

    public boolean validateExecutionDates(LocalDate date)
    {
        return date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now());
    }
}
