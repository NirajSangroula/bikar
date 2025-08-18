package com.pm.niraj.sharedlib.debezium;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import java.util.Optional;

public abstract class DebeziumTranslator<T> {
    private final DebeziumTransformerFactory<T> transformerFactory;
    @Getter
    @Setter
    @Value("${dbz.transformer.table}")
    private String tableName;

    public DebeziumTranslator(DebeziumTransformerFactory<T> transformerFactory) {
        this.transformerFactory = transformerFactory;
    }

    public T translateCreated(String value) throws JsonProcessingException {
        JsonNode payload = new ObjectMapper().readTree(value);
        if(payload != null){
            if(isOfferCreateStatement(payload) && payload.get("source").get("table").asText().equals(tableName)) {
                return transformerFactory.createDebeziumTranslator().transform(payload);
            }
        }
        return null;
    }

    private static boolean isOfferCreateStatement(JsonNode payload) {
        return Optional.ofNullable(payload.get("op"))
                .map(p -> p.asText().equals("c"))
                .orElse(false);
    }
}
