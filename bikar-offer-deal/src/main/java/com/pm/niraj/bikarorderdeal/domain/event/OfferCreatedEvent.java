package com.pm.niraj.bikarorderdeal.domain.event;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import lombok.Getter;
import org.springframework.data.domain.DomainEvents;

import java.lang.annotation.Annotation;

public class OfferCreatedEvent implements DomainEvent {
    @Getter
    private Offer offer;
    public OfferCreatedEvent(Offer offer) {
        this.offer = offer;
    }
}
