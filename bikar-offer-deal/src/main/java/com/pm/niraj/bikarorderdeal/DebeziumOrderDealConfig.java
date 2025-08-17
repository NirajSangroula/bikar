package com.pm.niraj.bikarorderdeal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.pm.niraj.sharedlib.debezium.CustomDebeziumParams;
import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
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
    private org.springframework.kafka.core.KafkaTemplate<String, OfferCreatedEvent> kafkaTemplate;

    @Autowired
    private DebeziumTranslator<OfferCreatedEvent> debeziumTranslator;


    @Bean
    @Primary
    public CustomDebeziumParams getCustomDebeziumParams() {
        return (key, value) -> {
            String eventValue = value;
            System.out.println("CDC event received: " + eventValue + " in topic " + kafkaTopic);
            // Optional: send event to Kafka topic "cdc-topic"
            if (kafkaTemplate != null) {
                try {
                    OfferCreatedEvent event = debeziumTranslator.translateCreated(eventValue);
                    if(event != null) {
                        kafkaTemplate.send(kafkaTopic, event);
                    }
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}
