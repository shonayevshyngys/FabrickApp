package com.github;

import com.github.common.FabrickClient;
import com.github.common.dtos.MoneyTransferRequestFactory;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;

@SpringBootTest(classes = {FabrickClient.class, MoneyTransferRequestFactory.class})
@TestPropertySource("classpath:application.properties")
@Log4j2
class FabrickE2ETest {

        @Autowired
        FabrickClient client;

        @Autowired
        MoneyTransferRequestFactory factory;

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
            var body = factory.getDummyItem();
            var res = client.createMoneyTransfer(body);
            log.info(res.getStatus() + " " + res.getPayload().getMoneyTransferId());
            Assertions.assertAll(

                    // can't properly test everything, so status not null should be tested
                    () -> Assertions
                            .assertEquals("Il conto beneficiario non puo' essere uguale a conto ordinante, di addebito o di addebito spese",
                                    res.getErrors().get(0).getDescription())
            );
        }
}

