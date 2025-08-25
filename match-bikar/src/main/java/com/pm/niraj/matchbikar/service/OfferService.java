package com.pm.niraj.matchbikar.service;

import com.pm.niraj.matchbikar.entity.Offer;
import com.pm.niraj.matchbikar.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {
    OfferRepository offerRepository;
    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public Offer save(Offer offer) {
        return this.offerRepository.save(offer);
    }
}
