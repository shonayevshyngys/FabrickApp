package com.github.fabrickpaymentquery;

import com.github.fabrickpaymentquery.model.Transfer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.kstream.KTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
@Log4j2
@OpenAPIDefinition(info =
@Info(title = "Query API", version = "1.0", description = "Documentation Query API v1.0"),
        servers = {
        @Server(url = "/query", description = "Fabrick Query server")
}
)
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
