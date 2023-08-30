package com.github.fabrickapp.controllers;

import com.github.fabrickapp.client.FabrickClient;
import com.github.fabrickapp.domain.model.Transfer;
import com.github.fabrickapp.dtos.MiddlewareMoneyTransferBodyDTO;
import com.github.fabrickapp.dtos.MoneyTransferRequestFactory;
import com.github.fabrickapp.dtos.fabrickdtos.MoneyTransferDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.AccountPayloadDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.BalancePayloadDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.ListDTO;
import com.github.fabrickapp.dtos.fabrickdtos.reqres.transaction.TransactionDTO;
import com.github.fabrickapp.service.ITransferService;
import com.github.fabrickapp.service.validators.DateValidator;
import com.github.fabrickapp.service.validators.TransferValidator;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
public class FabrickController {

    final FabrickClient client;
    final MoneyTransferRequestFactory factory;
    final DateValidator dateValidator;
    final TransferValidator transferValidator;
    final ITransferService service;

    public FabrickController(DateValidator dateValidator, FabrickClient client, MoneyTransferRequestFactory factory, TransferValidator transferValidator, ITransferService service) {
        this.dateValidator = dateValidator;
        this.client = client;
        this.factory = factory;
        this.transferValidator = transferValidator;
        this.service = service;
    }

    @GetMapping("/balance")
    public ResponseEntity<BalancePayloadDTO> getBalance()
    {
        var fabrickRes = client.getBalance();
        return new ResponseEntity<>(fabrickRes.getPayload(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/account")
    public ResponseEntity<AccountPayloadDTO> getAccountData()
    {
        var fabrickRes = client.getAccount();
        return new ResponseEntity<>(fabrickRes.getPayload(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/transactions")
    public ResponseEntity<ListDTO<TransactionDTO>> getTransactions(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to
    )
    {
        boolean validated = dateValidator.validateDates(from, to.orElse(LocalDate.now()));
        if (!validated) return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        var fabrickRes = client.getTransactions(from, to.orElse(LocalDate.now()));
        return new ResponseEntity<>(fabrickRes.getPayload(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/transfer")
    public ResponseEntity<MoneyTransferDTO> createTransfer(@RequestBody @Valid MiddlewareMoneyTransferBodyDTO dto)
    {
        boolean validated  = dateValidator.validateExecutionDates(dto.getExecutionDate());
        validated &= transferValidator.validateMiddlewareDTO(dto);
        if (!validated) throw new RuntimeException("Date or code validation failed");
        var fabrickRes = client.createMoneyTransfer(factory.translateToFabrickDTO(dto));
        service.save(fabrickRes);
        return new ResponseEntity<>(fabrickRes, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/failedTransactions")
    public ResponseEntity<List<Transfer>> getFailedTransactions()
    {
        return new ResponseEntity<>(service.getAllFailed(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/executedTransactions")
    public ResponseEntity<List<Transfer>> getExecutedTransactions()
    {
        return new ResponseEntity<>(service.getAllExecuted(), HttpStatusCode.valueOf(200));
    }


    //TODO
    /*
    1) Return erros on 400 +
    2) OpenAPI support +
    3) Saving to db
    4) Docker-compose
    5) Structured logging
     */



}
