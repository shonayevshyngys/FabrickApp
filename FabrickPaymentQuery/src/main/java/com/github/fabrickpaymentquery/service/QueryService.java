package com.github.fabrickpaymentquery.service;

import com.github.fabrickpaymentquery.model.Transfer;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class QueryService {

    private final InteractiveQueryService interactiveQueryService;

    public QueryService(InteractiveQueryService interactiveQueryService) {
        this.interactiveQueryService = interactiveQueryService;
    }

    public Map<String, Transfer> getQuery(String storeName)
    {
        Map<String, Transfer> res = new HashMap<>();
        ReadOnlyKeyValueStore<String, Transfer> store = interactiveQueryService.getQueryableStore(storeName, QueryableStoreTypes.keyValueStore());
        KeyValueIterator<String , Transfer> iterator = store.all();
        while (iterator.hasNext()) {
            KeyValue<String, Transfer> entry = iterator.next();
            res.put(entry.key, entry.value);
        }
        return res;
    }
}
