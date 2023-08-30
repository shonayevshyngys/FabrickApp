package com.github.fabrickapp.service;

import com.github.fabrickapp.domain.model.Transfer;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TransferEntityFactory {
    public Transfer createTransferEntityFromDTO(MoneyTransferDTO dto) {
        Transfer transfer = new Transfer();
        transfer.setStatus(dto.getStatus());
        transfer.setDate(LocalDate.now());

        if (dto.getStatus().equals("OK")) {
            var payload = dto.getPayload();
            transfer.setTransferId(payload.getMoneyTransferId());
            transfer.setTransferCode(payload.getCreditor().getAccount().getAccountCode());
            transfer.setTransferStatus(payload.getStatus());
            transfer.setTransferDirection(payload.getDirection());
            transfer.setTransferCurrency(payload.getAmount().getCreditorCurrency());
            transfer.setTransferAmount(payload.getAmount().getCreditorAmount());
            transfer.setBeneficiaryName(payload.getCreditor().getName());
        }
        else {
            transfer.setErrorCode(dto.getErrors().get(0).getCode());
            transfer.setErrorDesc(dto.getErrors().get(0).getDescription());
        }

        return transfer;
    }
}
