package com.pm.niraj.bikarorderdeal.api;
import com.pm.niraj.bikarorderdeal.BikarOrderDealApplication;
import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.dto.OfferDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BikarOrderDealApplication.class
)
@ActiveProfiles("test")
public class OfferDealControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private final String BASE_URL = "http://localhost:";

    // If there are other beans in DebeziumConfig that are injected anywhere,
    // you can @MockBean them too here.

    @Test
    void contextLoads() {
        System.out.println("Port = " + port);
        assertTrue(port > 0);
    }

    @Test
    public void test__CreatingOffer__SavesOfferInDatabase() {
        OfferDto dto = OfferDto.builder()
                .title("title")
                .description("description")
                .offerType(OfferType.ONE_TIME.toString())
                .providerId(1L)
                .build();

        ResponseEntity<Offer> response = restTemplate.postForEntity(
                BASE_URL + port + "/offer/create",
                dto,
                Offer.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dto.getTitle(), response.getBody().getTitle());
        assertEquals(dto.getDescription(), response.getBody().getDescription());
        assertTrue(response.getBody().getId() > 0);
        assertTrue(response.getBody().getProvider().getId() > 0);
    }

    @Test
    public void test__CreatingOffer__CreatesCorrectStatus() {
        OfferDto dto = OfferDto.builder()
                .title("title")
                .description("description")
                .offerType(OfferType.ONE_TIME.toString())
                .providerId(1L)
                .build();

        ResponseEntity<Offer> response = restTemplate.postForEntity(
                BASE_URL + port + "/offer/create",
                dto,
                Offer.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(OfferStatus.CREATED, response.getBody().getStatus());
    }
}
