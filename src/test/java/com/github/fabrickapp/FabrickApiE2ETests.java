package com.github.fabrickapp;

import com.github.fabrickapp.client.FabrickClient;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferRequestDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer.TransferAccountDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.mtransfer.TransferCreditorDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

@SpringBootTest
@TestPropertySource("classpath:application.properties")
class FabrickApiE2ETests {

    @Autowired
    FabrickClient client;

    @Test
    void FabrickApiE2EBalanceTest()
    {
        var res = client.getBalance();
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK", res.getStatus()),
                () -> Assertions.assertNotNull(res.getPayload()),
                () -> Assertions.assertNull(res.getErrors())
        );
    }

    @Test
    void FabrickApiE2EAccountTest()
    {
        var res = client.getAccount();
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK", res.getStatus()),
                () -> Assertions.assertNotNull(res.getPayload()),
                () -> Assertions.assertNull(res.getErrors())
        );
    }

    @Test
    void FabrickApiE2ETransactionsTest()
    {
        var res = client.getTransactions(LocalDate.now().minusMonths(2), LocalDate.now());
        Assertions.assertAll(
                () -> Assertions.assertEquals("OK", res.getStatus()),
                () -> Assertions.assertNotNull(res.getPayload()),
                () -> Assertions.assertNull(res.getErrors()),
                () -> Assertions.assertNotNull(res.getPayload().getList())
        );
    }

    @Test
    void FabrickApiE2ECreateMoneyTransferTest()
    {
        var body = getDummyItem();
        var res = client.createMoneyTransfer(body);
        Assertions.assertAll(

                // can't properly test everything, so status not null should be tested
                () -> Assertions.assertNotNull(res.getStatus())
        );
    }

    MoneyTransferRequestDTO getDummyItem()
    {
        var body = new MoneyTransferRequestDTO();
        body.setCreditor(TransferCreditorDTO
                        .builder()
                        .name("John Doe")
                        .account(TransferAccountDTO
                                .builder()
                                .accountCode("IT23A0336844430152923804660")
                                .build())
                .build());
        body.setExecutionDate(LocalDate.now());
        body.setDescription("Some random desc");
        body.setAmount(12d);
        body.setCurrency("EUR");
        return body;
    }
}
