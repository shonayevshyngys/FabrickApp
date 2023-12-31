package com.github.paymentprocessor;

import com.github.common.dtos.fabrickdtos.FabrickError;
import com.github.common.dtos.fabrickdtos.MoneyTransferDTO;
import com.github.paymentprocessor.model.Transfer;
import com.github.paymentprocessor.service.TransferEntityFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class TransferFactoryTest {

    TransferEntityFactory factory = new TransferEntityFactory();
    @Test
    void checkErrorTransfer()
    {
        //prepare values
        MoneyTransferDTO dto = new MoneyTransferDTO();
        dto.setStatus("KO");

        FabrickError error = new FabrickError();
        error.setCode("REQ500");
        error.setDescription("Some generic error");
        List<FabrickError> list = new ArrayList<>();
        list.add(error);
        dto.setErrors(list);

        dto.setPayload(null);

        Transfer transfer = factory.createTransferEntityFromDTO(null, dto);

        Assertions.assertAll(

                () -> Assertions.assertNotNull(transfer.getStatus()),
                () -> Assertions.assertNotNull(transfer.getErrorDesc()),
                () -> Assertions.assertNotNull(transfer.getErrorCode()),

                () -> Assertions.assertNull(transfer.getTransferStatus()),
                () -> Assertions.assertNull(transfer.getTransferCurrency()),
                () -> Assertions.assertNull(transfer.getTransferDirection()),
                () -> Assertions.assertNull(transfer.getTransferCode())
        );

    }
}
