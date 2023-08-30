package com.github.fabrickapp.service;

import com.github.fabrickapp.domain.model.Transfer;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferDTO;
import com.github.fabrickapp.service.repo.TransferRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService implements ITransferService{

    final TransferRepository repository;
    final TransferEntityFactory factory;

    public TransferService(TransferRepository repository, TransferEntityFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }


    @Override
    public Transfer save(MoneyTransferDTO transferDTO) {
        var transfer = factory.createTransferEntityFromDTO(transferDTO);
        return repository.save(transfer);
    }

    @Override
    public List<Transfer> getAllExecuted() {
        return repository.findAllByStatusIsNotNullAndErrorCodeIsNull();
    }

    @Override
    public List<Transfer> getAllFailed() {
        return repository.findAllByStatusIsNotNullAndErrorCodeIsNotNull();
    }
}
