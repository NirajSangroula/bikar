package com.pm.niraj.bikarorderdeal.publisher;


import com.pm.niraj.bikarorderdeal.domain.event.DomainEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DummyDomainEventPublisher extends DomainEventPublisher{
    @Override
    public void publish(Object aggregate, List<DomainEvent> events) {
        System.out.println("Published events: " + events + " to aggregate: " + aggregate);
    }
}
