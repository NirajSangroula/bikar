package com.pm.niraj.sharedlib.debezium;

import com.fasterxml.jackson.databind.JsonNode;

@FunctionalInterface
public interface Transformer<T> {
    T transform(JsonNode cdcPayload);
}
