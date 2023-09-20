package com.github.fabrickpaymentquery;

import com.github.fabrickpaymentquery.model.Transfer;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Log4j2
public class FabrickPaymentQuery {


    public static void main(String[] args) {
        SpringApplication.run(FabrickPaymentQuery.class, args);
    }

    @Bean
    public Consumer<KStream<String, Transfer>> successfulPayments() {

        return input -> input.foreach((key,value) -> log.info("Successful payment {} received {}", key,  value.getUuid()));
    }

    @Bean
    public Consumer<KStream<String, Transfer>> declinedPayments() {

        return input -> {
//            input.toTable(Materialized.as("decl-store"));
            input.foreach((key,value) -> log.info("Declined payment {} received {}", key, value.getErrorDesc()));
        };
    }

    @Bean
    public Consumer<KStream<String, Transfer>> failedPayments() {

        return input -> input.foreach((key,value) -> log.info("Successful payment {} received {}", key,  value.getErrorDesc()));
    }
}
