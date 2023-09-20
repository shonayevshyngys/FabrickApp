package com.github.paymentprocessor;

import com.github.common.FabrickClient;
import com.github.common.dtos.MiddlewareMoneyTransferBodyDTO;
import com.github.common.dtos.MoneyTransferRequestFactory;
import com.github.paymentprocessor.model.Transfer;
import com.github.paymentprocessor.service.TransferEntityFactory;
import com.github.paymentprocessor.service.TransferService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.function.Function;

@SpringBootApplication
@Log4j2
@ComponentScan("com.github.*")
public class PaymentProcessorApplication {

    private final FabrickClient client;

    private final MoneyTransferRequestFactory factory;

    private final TransferEntityFactory transferEntityFactory;

    private final TransferService service;

    private static final Marker PROCESSOR_MARKER = MarkerManager.getMarker("Payment processor");

    public PaymentProcessorApplication(FabrickClient client, MoneyTransferRequestFactory factory, TransferEntityFactory transferEntityFactory, TransferService service) {
        this.client = client;
        this.factory = factory;
        this.transferEntityFactory = transferEntityFactory;
        this.service = service;
    }

    @Bean
    @SuppressWarnings("unchecked")
    //https://docs.spring.io/spring-cloud-stream/docs/4.0.4-SNAPSHOT/reference/html/spring-cloud-stream-binder-kafka.html#_multiple_output_bindings
    public Function<KStream<String, MiddlewareMoneyTransferBodyDTO>, KStream<String, Transfer>[]> process() {

        return input -> {
            var flatMap = input.map((key, value) -> {
                        Transfer transfer;
                        try {
                            var res = client.createMoneyTransfer(factory.translateToFabrickDTO(value));
                            transfer = service.save(key, res);
                            log.info(PROCESSOR_MARKER, "Saved payment {} to a db {}", transfer.getId(), transfer.getUuid());
                        } catch (Exception e) {
                            String message = e.getMessage() + " " + key;
                            transfer = new Transfer();
                            transfer.setStatus("null");
                            transfer.setErrorDesc(message);
                            log.error(PROCESSOR_MARKER, message, e);
                        }
                        return new KeyValue<>(key, transfer);
                    })
                    .split()
                    .branch((k, v) -> v.getStatus().equals("null"))  //process-out-1 ??
                    .branch((k, v) -> v.getStatus().equals("KO")) //process-out-2 ??????????
                    .branch((k, v) -> v.getStatus().equals("OK")) //process-out-0 ???
                    .noDefaultBranch();
            return flatMap.values().toArray(new KStream[0]);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessorApplication.class, args);
    }


}
