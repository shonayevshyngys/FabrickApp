package com.github.fabrickapp.dtos;

import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferRequestDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer.TransferAccountDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer.TransferCreditorDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class MoneyTransferRequestFactory {

    public MoneyTransferRequestDTO translateToFabrickDTO(MiddlewareMoneyTransferBodyDTO dto)
    {
        MoneyTransferRequestDTO request = getDummyItem();
        request.getCreditor().setName(dto.getReceiverName());
        request.getCreditor().getAccount().setAccountCode(dto.getAccountCode());
        request.setAmount(dto.getAmount());
        request.setDescription(dto.getDescription());
        request.setCurrency(dto.getCurrency());
        if (dto.getExecutionDate() != null ) request.setExecutionDate(dto.getExecutionDate().toString());
        else request.setExecutionDate(LocalDate.now().toString());
        return request;
    }

    public MoneyTransferRequestDTO getDummyItem()
    {
        var body = new MoneyTransferRequestDTO();
        body.setCreditor(TransferCreditorDTO
                .builder()
                .name("John Doe")
                .account(TransferAccountDTO
                        .builder()
                        .accountCode("IT40L0326822311052923800661")
                        .build())
                .build());
        body.setExecutionDate(LocalDate.now().toString());
        body.setDescription("Some random desc");
        body.setAmount(0.1d);
        body.setCurrency("EUR");
        return body;
    }

}
