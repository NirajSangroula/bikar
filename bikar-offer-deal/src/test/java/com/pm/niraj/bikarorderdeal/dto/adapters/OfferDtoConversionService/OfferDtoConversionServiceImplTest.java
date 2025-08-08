package com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoConversionService;

import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferDtoConversionServiceImplTest {
    @Test
    public void test__getOfferStatus(){
        OfferDtoConversionService offerDtoConversionService = new OfferDtoConversionServiceImpl();
        OfferStatus status = offerDtoConversionService.getOfferStatus(OfferStatus.CREATED.toString());
        assertEquals(OfferStatus.CREATED, status);
    }

    @Test
    public void test__getOfferType(){
        OfferDtoConversionService offerDtoConversionService = new OfferDtoConversionServiceImpl();
        OfferType type = offerDtoConversionService.getOfferType(OfferType.ONE_TIME.toString());
        assertEquals(OfferType.ONE_TIME, type);
    }
}
