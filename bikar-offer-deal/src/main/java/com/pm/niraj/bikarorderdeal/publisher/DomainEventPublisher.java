package com.pm.niraj.bikarorderdeal.publisher;

import com.pm.niraj.bikarorderdeal.domain.event.DomainEvent;

import java.util.List;

public abstract class DomainEventPublisher {
    public abstract void publish(Object aggregate, List<DomainEvent> events);
}
