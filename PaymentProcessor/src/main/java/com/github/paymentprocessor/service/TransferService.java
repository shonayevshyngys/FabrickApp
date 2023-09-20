package com.github.paymentprocessor.service;

import com.github.common.dtos.fabrickdtos.MoneyTransferDTO;
import com.github.paymentprocessor.model.Transfer;
import com.github.paymentprocessor.service.repo.TransferRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Log4j2
public class TransferService{

    final TransferRepository repository;
    final TransferEntityFactory factory;

    private static final Marker TRANSFER_SERVICE_MARKER = MarkerManager.getMarker("Service");

    public TransferService(TransferRepository repository, TransferEntityFactory factory) {
        this.repository = repository;
        this.factory = factory;
    }


    public Transfer save(String uuid, MoneyTransferDTO transferDTO) {
        var transfer = factory.createTransferEntityFromDTO(uuid, transferDTO);
        var dbRes = repository.save(transfer);
        log.info(TRANSFER_SERVICE_MARKER,"Saved new transfer with id {}", dbRes.getId());
        return dbRes;
    }

}
