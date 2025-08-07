package com.pm.niraj.bikarorderdeal.application;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.infrastructure.OfferRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferApplicationService {

    @Autowired
    private OfferRepository offerRepository;
    public void createOffer(final Offer offer) {
        offerRepository.save(offer);
    }
}
