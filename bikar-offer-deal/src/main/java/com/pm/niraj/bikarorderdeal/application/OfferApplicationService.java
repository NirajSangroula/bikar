package com.pm.niraj.bikarorderdeal.application;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.infrastructure.OfferRepository;
import com.pm.niraj.sharedlib.publisher.DomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferApplicationService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private DomainEventPublisher publisher;
    public Optional<Offer> createOffer(final Offer offer) {
        offer.setStatus(OfferStatus.CREATED);
        Offer savedOffer = offerRepository.save(offer);
        //Use debezium to properly do stuff here: transactional log trailing ok
        return Optional.of(savedOffer);
    }

    public Optional<Offer> getOffer(Long id) {
        return offerRepository.findById(id);
    }
}
