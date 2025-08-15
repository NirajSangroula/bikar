package com.pm.niraj.bikarorderdeal;

import com.pm.niraj.sharedlib.debezium.CustomDebeziumParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Profile("!test")
@ComponentScan("com.pm.niraj.customdebezium")
public class DebeziumOrderDealConfig{
    @Value("${cdc-topic.name}")
    private String kafkaTopic;
    @Autowired(required = false)
    private org.springframework.kafka.core.KafkaTemplate<String, String> kafkaTemplate;


    @Bean
    @Primary
    public CustomDebeziumParams getCustomDebeziumParams() {
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
