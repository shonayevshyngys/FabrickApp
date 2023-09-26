package com.github.controllers;

import com.github.common.FabrickClient;
import com.github.common.dtos.MoneyTransferRequestFactory;
import com.github.common.dtos.fabrickdtos.reqres.AccountPayloadDTO;
import com.github.common.dtos.fabrickdtos.reqres.BalancePayloadDTO;
import com.github.common.dtos.fabrickdtos.reqres.ListDTO;
import com.github.common.dtos.fabrickdtos.reqres.transaction.TransactionDTO;
import com.github.common.validators.DateValidator;
import com.github.common.validators.TransferValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@Log4j2
public class FabrickController {

    final FabrickClient client;
    final MoneyTransferRequestFactory factory;
    final DateValidator dateValidator;
    final TransferValidator transferValidator;


    private static final Marker CONTROLLER_VALIDATION_MARKER = MarkerManager.getMarker("Controller validation");

    public FabrickController(DateValidator dateValidator, FabrickClient client, MoneyTransferRequestFactory factory, TransferValidator transferValidator) {
        this.dateValidator = dateValidator;
        this.client = client;
        this.factory = factory;
        this.transferValidator = transferValidator;
    }

    @GetMapping("/balance")
    @Operation(summary = "Get an account balance from Fabrick", description = "Returns balance of saved id")
    public ResponseEntity<BalancePayloadDTO> getBalance()
    {
        var fabrickRes = client.getBalance();
        return new ResponseEntity<>(fabrickRes.getPayload(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/account")
    @Operation(summary = "Get an account information from Fabrick", description = "Returns base information about Account")
    public ResponseEntity<AccountPayloadDTO> getAccountData()
    {
        var fabrickRes = client.getAccount();
        return new ResponseEntity<>(fabrickRes.getPayload(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/transactions")
    @Operation(summary = "Get list of transactions from Fabrick", description = "Returns a list of transactions specified by two dates. Second date is optional and would be today if not present")
    public ResponseEntity<ListDTO<TransactionDTO>> getTransactions(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> to
    )
    {
        boolean validated = dateValidator.validateDates(from, to.orElse(LocalDate.now()));
        if (!validated) {
            log.info(CONTROLLER_VALIDATION_MARKER,"Transactions validation failed");
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
        var fabrickRes = client.getTransactions(from, to.orElse(LocalDate.now()));
        return new ResponseEntity<>(fabrickRes.getPayload(), HttpStatusCode.valueOf(200));
    }


//    @GetMapping("/failedTransactions")
//    @Operation(summary = "Get list of failed transactions saved locally", description = "list of failed transactions saved locally")
//    public ResponseEntity<List<Transfer>> getFailedTransactions()
//    {
//        return new ResponseEntity<>(service.getAllFailed(), HttpStatusCode.valueOf(200));
//    }
//
//    @GetMapping("/executedTransactions")
//    @Operation(summary = "Get list of executed transactions saved locally", description = "list of executed transactions saved locally")
//    public ResponseEntity<List<Transfer>> getExecutedTransactions()
//    {
//        return new ResponseEntity<>(service.getAllExecuted(), HttpStatusCode.valueOf(200));
//    }
}
