package com.github.fabrickpaymentcommand;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info =
@Info(title = "Command API", version = "1.0", description = "Documentation Command API v1.0"),
        servers = {
                @Server(url = "/command", description = "Fabrick Command server")
        }
)
public class FabrickPaymentCommand {
    public static void main(String[] args) {
        SpringApplication.run(FabrickPaymentCommand.class, args);
    }
}
