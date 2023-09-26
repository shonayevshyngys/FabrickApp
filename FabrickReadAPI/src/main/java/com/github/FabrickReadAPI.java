package com.github;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(info =
		@Info(title = "Fabrick API", version = "1.0", description = "Documentation Fabrick API v1.0"),
		servers = {
			@Server(url = "/api", description = "Fabrick API server")
		}
)
@EnableCaching
public class FabrickReadAPI {

	public static void main(String[] args) {
		SpringApplication.run(FabrickReadAPI.class, args);
	}

}
