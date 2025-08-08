package com.pm.niraj.bikarorderdeal.api;

import com.pm.niraj.bikarorderdeal.application.OfferApplicationService;
import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.dto.OfferDto;
import com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoAdapter;
import com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoConversionService.OfferDtoConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/offer")
public class OfferController {
    private final OfferApplicationService offerApplicationService;
    private final OfferDtoAdapter  offerDtoAdapter;
    private final OfferDtoConversionService offerDtoConversionService;

    @Autowired
    public OfferController(OfferApplicationService offerApplicationService, OfferDtoAdapter offerDtoAdapter, OfferDtoConversionService offerDtoConversionService) {
        this.offerApplicationService = offerApplicationService;
        this.offerDtoAdapter = offerDtoAdapter;
        this.offerDtoConversionService = offerDtoConversionService;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id){
        if(id == -1L){
            throw new RuntimeException("invalid offer id");
        }
        return ResponseEntity.ok().body(new Offer());
    }

    @PostMapping("/create")
    public ResponseEntity<Offer> createOffer(@RequestBody OfferDto dto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(offerApplicationService
                        .createOffer(offerDtoAdapter.offerFrom(dto, offerDtoConversionService)).orElseThrow(RuntimeException::new));
    }
}