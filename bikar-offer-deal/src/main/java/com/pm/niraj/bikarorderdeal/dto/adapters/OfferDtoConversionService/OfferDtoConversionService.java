package com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoConversionService;

import com.pm.niraj.bikarorderdeal.domain.entity.Provider;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;

public interface OfferDtoConversionService {
    Provider getProviderById(Long providerId);
    OfferStatus getOfferStatus(String status);
    OfferType getOfferType(String offerType);
}
