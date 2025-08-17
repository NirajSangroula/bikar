package com.pm.niraj.sharedlib.event;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class OfferCreatedEvent implements DomainEvent {
    private long offerId;
    private Instant createdAt;
    private long providerId;
    public OfferCreatedEvent(long id, Instant createdAt, long providerId) {
        this.offerId = id;
        this.createdAt = createdAt;
        this.providerId = providerId;
    }

}
