package com.pm.niraj.bikarorderdeal.entity;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.domain.entity.Provider;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.infrastructure.OfferRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")
public class OfferTests {
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void test__canInsertData(){
        Offer offer = getOffer();
        var persistedOffer = offerRepository.save(offer);
        assertThat(persistedOffer.getId()).isGreaterThan(0);
        System.out.println(persistedOffer);

        assertThat(offerRepository.findById(persistedOffer.getId()))
                .hasValue(persistedOffer);
    }

    public static Offer getOffer() {
        Offer offer = Offer.builder()
                .title("My offer")
                .offerType(OfferType.ONE_TIME)
                .provider(new Provider())
                .description("Whatever Description")
                .status(OfferStatus.CREATED).build();
        return offer;
    }
}
