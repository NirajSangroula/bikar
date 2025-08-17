package com.pm.niraj.bikarorderdeal;

import com.fasterxml.jackson.databind.JsonNode;

@FunctionalInterface
public interface Transformer<T> {
    T transform(JsonNode cdcPayload);
}
