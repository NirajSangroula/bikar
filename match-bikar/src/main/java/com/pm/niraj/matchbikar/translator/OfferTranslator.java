package com.pm.niraj.matchbikar.translator;

import com.pm.niraj.matchbikar.entity.Offer;
import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OfferTranslator implements Translator<Offer, OfferCreatedEvent> {
    @Override
    public Offer  translate(final OfferCreatedEvent event){
        return Offer.builder()
                .id(event.getOfferId())
                .offerType(event.getOfferType())
                .title(event.getTitle())
                .description(event.getDescription())
                .status(event.getStatus())
                .providerId(event.getProviderId())
                .createdAt(event.getCreatedAt())
                .build();
    }
}