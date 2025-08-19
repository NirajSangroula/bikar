package com.pm.niraj.matchbikar.kafka;

import com.pm.niraj.sharedlib.event.OfferCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OfferCreatedConsumer {
    @KafkaListener(topics = "${cdc-topic.offer.name}")
    public void listen(OfferCreatedEvent message){
        System.out.println("Received offer created event: " + message);
    }
}
