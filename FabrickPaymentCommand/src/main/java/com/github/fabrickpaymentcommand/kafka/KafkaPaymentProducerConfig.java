package com.github.fabrickpaymentcommand.kafka;

import com.github.common.dtos.MiddlewareMoneyTransferBodyDTO;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class KafkaPaymentProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String kafkaBroker;
    @Bean
    public ProducerFactory<String, MiddlewareMoneyTransferBodyDTO> producerFactory() {
        Map<String, Object> producerProps = new HashMap<>();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);

        // Define serializers
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        return new DefaultKafkaProducerFactory<>(producerProps);
    }
    @Bean
    public KafkaTemplate<String, MiddlewareMoneyTransferBodyDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
