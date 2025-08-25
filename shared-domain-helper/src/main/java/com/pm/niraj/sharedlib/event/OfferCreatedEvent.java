package com.pm.niraj.sharedlib.event;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OfferCreatedEvent implements DomainEvent {
    private long offerId;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant createdAt;
    private long providerId;
    String offerType;
    String status;
    String title;
    String description;
}
