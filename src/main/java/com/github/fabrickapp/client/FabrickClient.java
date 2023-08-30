
package com.github.fabrickapp.client;

import com.github.fabrickapp.dtos.fabrickdtos.AccountDTO;
import com.github.fabrickapp.dtos.fabrickdtos.BalanceDTO;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferDTO;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferRequestDTO;
import com.github.fabrickapp.dtos.fabrickdtos.TransactionsDTO;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@Log4j2
public class FabrickClient {
    private final WebClient client;
    private final long accountId;

    private static final Marker CLIENT_MARKER = MarkerManager.getMarker("Fabrick_Client");

    public FabrickClient(@Value("${apikey}") String apiKey, @Value("${url}") String url, @Value("${accountId}") String id) {
        accountId = Long.parseLong(id);
        client = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Auth-Schema", "S2S")
                .defaultHeader("apikey", apiKey)
                .defaultHeader("X-Time-Zone", "Europe/Rome")
                .build();
    }

    public BalanceDTO getBalance()
    {
        log.info(CLIENT_MARKER,"{} BALANCE REQUEST", accountId);
        return client.get()
                .uri("/api/gbs/banking/v4.0/accounts/%d/balance".formatted(accountId))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(BalanceDTO.class)).block();
    }

    public AccountDTO getAccount()
    {
        log.info(CLIENT_MARKER, "{} ACCOUNT REQUEST", accountId);
        return client.get()
                .uri("/api/gbs/banking/v4.0/accounts/%d".formatted(accountId))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(AccountDTO.class)).block();
    }

    public TransactionsDTO getTransactions(LocalDate from, LocalDate to)
    {
        log.info(CLIENT_MARKER, "{} TRANSACTIONS REQUEST", accountId);
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return client.get()
                .uri("/api/gbs/banking/v4.0/accounts/%d/transactions?fromAccountingDate=%s&toAccountingDate=%s"
                        .formatted(accountId, from.format(formatter), to.format(formatter) ))
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(TransactionsDTO.class)).block();
    }

    public MoneyTransferDTO createMoneyTransfer(MoneyTransferRequestDTO requestDTO)
    {
        log.info(CLIENT_MARKER, "{} POST MONEY TRANSFER REQUEST", accountId);
        return client.post()
                .uri("/api/gbs/banking/v4.0/accounts/%d/payments/money-transfers".formatted(accountId))
                .bodyValue(requestDTO)
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(MoneyTransferDTO.class)).block();
    }
}
