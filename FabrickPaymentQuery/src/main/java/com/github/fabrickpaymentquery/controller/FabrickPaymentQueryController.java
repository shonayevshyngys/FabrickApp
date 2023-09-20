package com.github.fabrickpaymentquery.controller;

import com.github.fabrickpaymentquery.model.Transfer;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@ComponentScan("com.github.common")
public class FabrickPaymentQueryController {

    private final InteractiveQueryService interactiveQueryService;

    public FabrickPaymentQueryController(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    @GetMapping("/declinedTransactions")
    public ResponseEntity<String> getDeclinedTransfers()
    {
        ReadOnlyKeyValueStore<String, Transfer> store = interactiveQueryService.getQueryableStore("decl-store", QueryableStoreTypes.keyValueStore());
        KeyValueIterator<String , Transfer> iterator = store.all();

        while (iterator.hasNext()) {
            KeyValue<String, Transfer> entry = iterator.next();
            log.info(entry.key + " + " + entry.value.getErrorDesc());
        }
        return ResponseEntity.ok("Kek");
    }
}
