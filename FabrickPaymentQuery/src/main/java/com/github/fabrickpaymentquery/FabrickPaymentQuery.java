package com.github.fabrickpaymentquery;

import com.github.fabrickpaymentquery.model.Transfer;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KTable;
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
    public Consumer<KTable<String, Transfer>> successfulPayments() {

        return input -> {
            //process it if needed
        };
    }

    @Bean
    public Consumer<KTable<String, Transfer>> declinedPayments() {

        return input -> {
            //process it if needed
        };
    }

    @Bean
    public Consumer<KTable<String, Transfer>> failedPayments() {

        return input -> {
            //process it if needed
        };
    }
}
