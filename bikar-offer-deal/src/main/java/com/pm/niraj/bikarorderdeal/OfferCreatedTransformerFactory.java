package com.pm.niraj.bikarorderdeal;

import com.fasterxml.jackson.databind.JsonNode;
import com.pm.niraj.sharedlib.debezium.DebeziumTransformerFactory;
import com.pm.niraj.sharedlib.debezium.Transformer;
import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Primary
@Component
public class OfferCreatedTransformerFactory implements DebeziumTransformerFactory<OfferCreatedEvent> {
    public Transformer<OfferCreatedEvent> createDebeziumTranslator(){
        return (payload) -> {
            JsonNode after = payload.get("after");
            return new OfferCreatedEvent(getPropertyLong("id", after),
                    Instant.ofEpochMilli(getPropertyLong("created_at", after)),
                    getPropertyLong("provider_id", after));
        };
    }

    private String getPropertyString(String property, JsonNode after) {
        return after.get(property).asText();
    }

    private Long getPropertyLong(String property, JsonNode after) {
        return after.get(property).asLong();
    }
}
