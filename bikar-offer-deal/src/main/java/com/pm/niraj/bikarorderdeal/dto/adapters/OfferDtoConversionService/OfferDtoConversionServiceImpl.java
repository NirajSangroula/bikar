package com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoConversionService;

import com.pm.niraj.bikarorderdeal.application.OfferApplicationService;
import com.pm.niraj.bikarorderdeal.domain.entity.Provider;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.infrastructure.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class OfferDtoConversionServiceImpl implements OfferDtoConversionService {
    @Override
    public Provider getProviderById(Long providerId) {
        return new Provider();
    }

    @Override
    public OfferStatus getOfferStatus(String status) {
        return OfferStatus.valueOf(status);
    }

    @Override
    public OfferType getOfferType(String offerType) {
        return OfferType.valueOf(offerType);
    }
}
