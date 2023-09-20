package com.github.fabrickpaymentcommand.controller;

import com.github.common.dtos.MiddlewareMoneyTransferBodyDTO;
import com.github.common.dtos.ResponseMessage;
import com.github.common.validators.DateValidator;
import com.github.common.validators.TransferValidator;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Log4j2
@ComponentScan("com.github.common")
public class FabrickPaymentCommandController {

    final DateValidator dateValidator;
    final TransferValidator transferValidator;

    private final KafkaTemplate<String, MiddlewareMoneyTransferBodyDTO> kafkaTemplate;
    private static final Marker CONTROLLER_VALIDATION_MARKER = MarkerManager.getMarker("Controller validation");

    public FabrickPaymentCommandController(DateValidator dateValidator, TransferValidator transferValidator, KafkaTemplate<String, MiddlewareMoneyTransferBodyDTO> kafkaTemplate) {
        this.dateValidator = dateValidator;
        this.transferValidator = transferValidator;
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/transfer")
    @Operation(summary = "Sends payment to payment processor", description = "This request consumes middle ware body to create a request to Fabrick for money transfer creation")
    public ResponseEntity<ResponseMessage> createTransfer(@RequestBody @Valid MiddlewareMoneyTransferBodyDTO dto)
    {
        boolean validated  = dateValidator.validateExecutionDates(dto.getExecutionDate());
        validated &= transferValidator.validateMiddlewareDTO(dto);
        if (!validated){
            log.info(CONTROLLER_VALIDATION_MARKER,"Transfer validation failed");
            throw new RuntimeException("Date or code validation failed");
        }
        kafkaTemplate.send("payment", UUID.randomUUID().toString(), dto);
        var res = ResponseMessage.builder()
                .code("201")
                .result("Your payment request is pending")
                .build();
        return new ResponseEntity<>(res, HttpStatusCode.valueOf(201));
    }
}
