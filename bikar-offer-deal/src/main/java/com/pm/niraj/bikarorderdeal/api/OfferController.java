package com.pm.niraj.bikarorderdeal.api;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/offer")
public class OfferController {
    @GetMapping("/get/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id){
        if(id == -1L){
            throw new RuntimeException("invalid offer id");
        }
        return ResponseEntity.ok().body(new Offer());
    }
}