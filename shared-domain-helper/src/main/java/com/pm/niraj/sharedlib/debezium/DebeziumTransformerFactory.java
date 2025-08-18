package com.pm.niraj.sharedlib.debezium;

public interface DebeziumTransformerFactory<T> {
    Transformer<T> createDebeziumTranslator();
}
