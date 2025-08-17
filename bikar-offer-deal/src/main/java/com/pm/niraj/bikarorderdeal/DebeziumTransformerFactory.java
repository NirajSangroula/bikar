package com.pm.niraj.bikarorderdeal;

import com.pm.niraj.sharedlib.event.OfferCreatedEvent;

public interface DebeziumTransformerFactory<T> {
    Transformer<T> createDebeziumTranslator();
}
