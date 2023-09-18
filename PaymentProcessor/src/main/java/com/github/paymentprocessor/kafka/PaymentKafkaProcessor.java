package com.github.paymentprocessor.kafka;

import com.github.common.FabrickClient;
import com.github.common.dtos.MiddlewareMoneyTransferBodyDTO;
import com.github.common.dtos.MoneyTransferRequestFactory;
import com.github.paymentprocessor.service.ITransferService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@ComponentScan("com.github.common")
public class PaymentKafkaProcessor {

    private final FabrickClient client;

    private final MoneyTransferRequestFactory factory;

    private final ITransferService service;

    private static final Marker PROCESSOR_MARKER = MarkerManager.getMarker("Payment processor");

    public PaymentKafkaProcessor(FabrickClient client, MoneyTransferRequestFactory factory, ITransferService service) {
        this.client = client;
        this.factory = factory;
        this.service = service;
    }

    @KafkaListener(topics = "payment", groupId = "kafkaGroup")
    public void listen(MiddlewareMoneyTransferBodyDTO message) {
       var res = client.createMoneyTransfer(factory.translateToFabrickDTO(message));
       var dbRes = service.save(res);
       log.info(PROCESSOR_MARKER, "Saved payment {} to a db {}", dbRes.getId(), dbRes.getTransferCode());
    }
}
