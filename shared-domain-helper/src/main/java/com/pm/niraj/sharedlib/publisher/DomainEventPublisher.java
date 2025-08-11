package com.pm.niraj.sharedlib.publisher;

import com.pm.niraj.sharedlib.event.DomainEvent;

import java.util.List;

public abstract class DomainEventPublisher {
    public abstract void publish(Object aggregate, List<DomainEvent> events);
}
