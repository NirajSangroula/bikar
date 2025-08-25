package com.pm.niraj.matchbikar.kafka;

import com.pm.niraj.matchbikar.service.OfferService;
import com.pm.niraj.matchbikar.translator.OfferTranslator;
import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OfferCreatedConsumer {
    @Autowired
    private OfferTranslator offerTranslator;
    @Autowired
    private OfferService offerService;

    @KafkaListener(topics = "${cdc-topic.offer.name}")
    public void listen(OfferCreatedEvent event){
        System.out.println("Received offer created event: " + event);
        offerService.save(offerTranslator.translate(event));
    }
}
