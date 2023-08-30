package com.github.fabrickapp.service;

import com.github.fabrickapp.domain.model.Transfer;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferDTO;

import java.util.List;

public interface ITransferService {
    public Transfer save(MoneyTransferDTO transferDTO);
    public List<Transfer> getAllExecuted();
    public List<Transfer> getAllFailed();

}
