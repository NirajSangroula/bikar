package com.pm.niraj.sharedlib.event;

import lombok.Getter;

@Getter
public class OfferCreatedEvent implements DomainEvent {
    private Object aggregate;
    public OfferCreatedEvent(Object aggregate) {
        this.aggregate = aggregate;
    }
}
