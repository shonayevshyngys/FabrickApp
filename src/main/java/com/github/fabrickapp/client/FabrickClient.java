package com.github.fabrickapp.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FabrickClient {
    private final WebClient client;

    public FabrickClient(@Value("${apikey}") String apiKey, @Value("${url}") String url) {
        client = WebClient.builder()
                .baseUrl(url)
                .defaultHeader("Auth-Schema", "S2S")
                .defaultHeader("apikey", apiKey)
                .build();
    }

    public void getBalance()
    {
        var res = client.get()
                .uri("/api/gbs/banking/v4.0/accounts")
                .exchangeToMono(clientResponse -> clientResponse.bodyToMono(String.class)).block();
        System.err.println(res);
    }
}
