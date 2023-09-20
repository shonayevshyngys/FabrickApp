package com.github.fabrickpaymentquery.controller;

import com.github.fabrickpaymentquery.model.Transfer;
import com.github.fabrickpaymentquery.service.QueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
@ComponentScan("com.github.common")
public class FabrickPaymentQueryController {

    private final QueryService service;

    public FabrickPaymentQueryController(QueryService service) {
        this.service = service;
    }

    @GetMapping("/declinedTransactions")
    @Operation(summary = "Get list of declined transactions reconstructed from Kafka", description = "List of declined (api call returned error) transactions")
    public ResponseEntity<Map<String,Transfer>> getDeclinedTransfers()
    {
        return ResponseEntity.ok(service.getQuery("decl-store"));
    }

    @GetMapping("/successfulTransactions")
    @Operation(summary = "Get list of successful transactions reconstructed from Kafka", description = "List of successful transactions")
    public ResponseEntity<Map<String,Transfer>> getSuccessfulTransfers()
    {
        return ResponseEntity.ok(service.getQuery("succ-store"));
    }

    @GetMapping("/failedTransactions")
    @Operation(summary = "Get list of successful transactions reconstructed from Kafka", description = "List of failed (internal error) transactions")
    public ResponseEntity<Map<String,Transfer>> getFailedTransfers()
    {
        return ResponseEntity.ok(service.getQuery("fail-store"));
    }
}
