package com.pm.niraj.bikarorderdeal;

import com.pm.niraj.customdebezium.CustomDebeziumParams;
import com.pm.niraj.customdebezium.DebeziumConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DebeziumOrderDealConfig extends DebeziumConfig {
    @Autowired(required = false)
    private org.springframework.kafka.core.KafkaTemplate<String, String> kafkaTemplate;


    @Override
    protected CustomDebeziumParams getCustomDebeziumParams() {
        return (key, value) -> {
            String eventValue = value;
            System.out.println("CDC event received: " + eventValue);
            // Optional: send event to Kafka topic "cdc-topic"
            if (kafkaTemplate != null) {
                kafkaTemplate.send("cdc-topic", eventValue);
            }
        };
    }
}
