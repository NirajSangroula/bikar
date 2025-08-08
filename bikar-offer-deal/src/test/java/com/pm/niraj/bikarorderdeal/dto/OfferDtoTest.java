package com.pm.niraj.bikarorderdeal.dto;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.domain.entity.Provider;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoAdapter;
import static org.junit.jupiter.api.Assertions.*;

import com.pm.niraj.bikarorderdeal.dto.adapters.OfferDtoConversionService.OfferDtoConversionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

public class OfferDtoTest {
    @Test
    public void test__CreatingDtoFromOffer__populatesAllField(){
        Provider provider = providerWithId1();
        Offer offer = Offer.builder()
                .offerType(OfferType.ONE_TIME)
                .description("a")
                .title("t")
                .status(OfferStatus.CREATED)
                .provider(provider).build();
        OfferDto dto = (new OfferDtoAdapter()).dtoFrom(offer);
        assertEquals(dto.getTitle(), offer.getTitle());
        assertEquals(dto.getDescription(), offer.getDescription());
        assertEquals(dto.getOfferType(), offer.getOfferType().toString());
        assertEquals(dto.getStatus(), offer.getStatus().toString());
        assertEquals(dto.getProviderId(), offer.getProvider().getId());
    }

    public static Provider providerWithId1() {
        Provider provider = Provider.builder().build();
        provider.setId(1L);
        return provider;
    }

    @Test
    public void test__CreatingOfferFromDto__populatesAllField(){
        OfferDto dto = OfferDto.builder()
                .offerType(OfferType.ONE_TIME.toString())
                .description("a")
                .title("t")
                .status(OfferStatus.CREATED.toString())
                .build();
        OfferDtoConversionService service = getMockedOfferDtoConversionService(dto);

        OfferDtoAdapter adapter = new OfferDtoAdapter();
        Offer offer = adapter.offerFrom(dto, service);
        assertEquals(offer.getTitle(), dto.getTitle());
        assertEquals(offer.getDescription(), dto.getDescription());
        assertEquals(OfferType.ONE_TIME, offer.getOfferType());
        assertEquals(OfferStatus.CREATED, offer.getStatus());
        assertEquals(providerWithId1().getId(), offer.getProvider().getId());

    }

    private static OfferDtoConversionService getMockedOfferDtoConversionService(OfferDto dto) {
        OfferDtoConversionService service = mock(OfferDtoConversionService.class);
        when(service.getOfferStatus(dto.getStatus())).thenReturn(OfferStatus.CREATED);
        when(service.getOfferType(dto.getOfferType())).thenReturn(OfferType.ONE_TIME);
        when(service.getProviderById(dto.getProviderId())).thenReturn(providerWithId1());
        return service;
    }
}
