package com.github.service;

import com.github.common.FabrickClient;
import com.github.common.dtos.fabrickdtos.AccountDTO;
import com.github.common.dtos.fabrickdtos.BalanceDTO;
import com.github.common.dtos.fabrickdtos.TransactionsDTO;
import com.github.common.dtos.fabrickdtos.reqres.BalancePayloadDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FabrickService {
    final FabrickClient client;

    public FabrickService(FabrickClient client) {
        this.client = client;
    }

    @Cacheable(value = "Balance")
    public BalanceDTO getBalance()
    {
        return client.getBalance();
    }

    @Cacheable(value = "Account")
    public AccountDTO getAccount()
    {
        return client.getAccount();
    }

    @Cacheable(value = "Transactions", key = "#from.toString()+#to.toString()")
    public TransactionsDTO getTransactions(LocalDate from, LocalDate to)
    {
        return client.getTransactions(from, to);
    }

}
