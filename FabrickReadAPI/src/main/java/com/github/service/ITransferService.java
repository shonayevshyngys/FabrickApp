package com.github.service;

import com.github.domain.model.Transfer;
import com.github.common.dtos.fabrickdtos.MoneyTransferDTO;
import java.util.List;

public interface ITransferService {
    public Transfer save(MoneyTransferDTO transferDTO);
    public List<Transfer> getAllExecuted();
    public List<Transfer> getAllFailed();

}
