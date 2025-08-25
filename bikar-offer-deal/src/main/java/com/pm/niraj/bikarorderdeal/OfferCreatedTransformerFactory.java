package com.pm.niraj.bikarorderdeal;

import com.fasterxml.jackson.databind.JsonNode;
import com.pm.niraj.bikarorderdeal.application.OfferApplicationService;
import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.sharedlib.debezium.DebeziumTransformerFactory;
import com.pm.niraj.sharedlib.debezium.Transformer;
import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;

@Primary
@Component
public class OfferCreatedTransformerFactory implements DebeziumTransformerFactory<OfferCreatedEvent> {
    public Transformer<OfferCreatedEvent> createDebeziumTranslator(){
        return (payload) -> {
            JsonNode after = payload.get("after");
            return new OfferCreatedEvent(getPropertyLong("id", after),
                    getPropertyInstant("created_at", after),
                    getPropertyLong("provider_id", after),
                    getPropertyString("offer_type", after),
                    getPropertyString("status", after),
                    getPropertyString("title", after),
                    getPropertyString("description", after));
        };
    }

    private String getPropertyString(String property, JsonNode after) {
        return after.get(property).asText();
    }

    private Long getPropertyLong(String property, JsonNode after) {
        return after.get(property).asLong();
    }
    private Instant getPropertyInstant(String property, JsonNode after) {
        BigDecimal bigDecimal = new BigDecimal(getPropertyString(property, after));
        long micros = bigDecimal.longValue();
        long seconds = micros / 1_000_000;
        long microsRemainder = micros % 1_000_000;
        return Instant.ofEpochSecond(seconds, microsRemainder * 1000);
    }
}
