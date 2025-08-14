package com.pm.niraj.customdebezium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!test")
@ConditionalOnProperty(name = "debezium.enabled", havingValue = "true", matchIfMissing = false)
public class DebeziumOrderDealConfig extends DebeziumConfig {
    @Value("${cdc-topic.name}")
    private String kafkaTopic;
    @Autowired(required = false)
    private org.springframework.kafka.core.KafkaTemplate<String, String> kafkaTemplate;


    @Override
    protected CustomDebeziumParams getCustomDebeziumParams() {
        return (key, value) -> {
            String eventValue = value;
            System.out.println("CDC event received: " + eventValue + " in topic " + kafkaTopic);
            // Optional: send event to Kafka topic "cdc-topic"
            if (kafkaTemplate != null) {
                kafkaTemplate.send(kafkaTopic, eventValue);
            }
        };
    }
}
