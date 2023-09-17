package com.github.paymentprocessor;

import com.github.common.dtos.fabrickdtos.MoneyTransferDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class PaymentKafkaProcessor {
    @KafkaListener(topics = "payment", groupId = "kafkaGroup")
    public void listen(MoneyTransferDTO message) {
        // Handle the received Kafka message
        log.info("Received message from Kafka: /n" + message.toString());
        // Perform your processing here
    }
}
