package com.pm.niraj.bikarorderdeal.entity;

import com.pm.niraj.bikarorderdeal.domain.entity.Compensation;
import com.pm.niraj.bikarorderdeal.domain.entity.Deal;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.CompensationType;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.DealType;
import com.pm.niraj.bikarorderdeal.infrastructure.DealRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(properties = "spring.flyway.enabled=false")
public class OfferDealTest {
    @Autowired
    private DealRepository  dealRepository;
    @Autowired
    private TestEntityManager testEntityManager;
//    @Test
//    public void test__compensation_OfferAndProviderIsCreatedWithDeal(){
//        Compensation compensation = Compensation.builder()
//                .type(CompensationType.CASH)
//                .build();
//        Deal deal = Deal.builder()
//                .compensation(compensation)
//                .dealType(DealType.NON_NEGOTIABLE)
//                .offer(OfferTests.getOffer())
//                .build();
//        Deal persistedDeal = dealRepository.save(deal);
//        testIdExists(persistedDeal.getId());
//        testIdExists(persistedDeal.getCompensation().getId());
//        testIdExists(persistedDeal.getOffer().getId());
//        testIdExists(persistedDeal.getOffer().getProvider().getId());
//    }

    private static void testIdExists(Long persistedDeal) {
        Assertions.assertThat(persistedDeal).isGreaterThan(0);
    }
}
