package com.pm.niraj.bikarorderdeal.dto.adapters;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.dto.OfferDto;
import com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoConversionService.OfferDtoConversionService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OfferDtoAdapter {
    public OfferDto dtoFrom(final Offer offer) {
        return OfferDto.builder()
                .offerType(offer.getOfferType().toString())
                .providerId(offer.getProvider().getId())
                .status(offer.getStatus().toString())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .build();
    }
    public Offer offerFrom(OfferDto dto, OfferDtoConversionService service) {
        return Offer.builder()
                .provider(service.getProviderById(dto.getProviderId()))
                .offerType(service.getOfferType(dto.getOfferType()))
                .title(dto.getTitle())
                .status(Optional.ofNullable(dto.getStatus())
                        .map(status -> OfferStatus.valueOf(status))
                        .orElse(null))
                .description(dto.getDescription()).build();
    }
}
