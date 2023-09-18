package com.github.paymentprocessor.service;

import com.github.common.dtos.fabrickdtos.MoneyTransferDTO;
import com.github.paymentprocessor.model.Transfer;

public interface ITransferService {
    public Transfer save(MoneyTransferDTO transferDTO);
}
