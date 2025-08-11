package com.pm.niraj.bikarorderdeal.api;

import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import static org.junit.jupiter.api.Assertions.*;

import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.dto.OfferDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.InitBinder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OfferDealControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    // Construct base URL dynamically using the injected port
    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    // Uncomment if you want to test only in "prod" profile
    // @Test
    public void test_AnyRuntimeException_InvokesControllerAdvice() {
        ResponseEntity<String> response = restTemplate.getForEntity(getBaseUrl() + "/offer/get/-1", String.class);
        assertEquals("invalid offer id", response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void test_CreatingOffer_SavesOfferInDatabase() {
        OfferDto dto = OfferDto.builder()
                .title("title")
                .description("description")
                .offerType(OfferType.ONE_TIME.toString())
                .providerId(1L)
                .build();

        ResponseEntity<Offer> response = restTemplate.postForEntity(getBaseUrl() + "/offer/create", dto, Offer.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dto.getTitle(), response.getBody().getTitle());
        assertEquals(dto.getDescription(), response.getBody().getDescription());
        assertTrue(response.getBody().getId() > 0);
        assertTrue(response.getBody().getProvider().getId() > 0);
    }

    @Test
    public void test_CreatingOffer_CreatesCorrectStatus() {
        OfferDto dto = OfferDto.builder()
                .title("title")
                .description("description")
                .offerType(OfferType.ONE_TIME.toString())
                .providerId(1L)
                .build();

        ResponseEntity<Offer> response = restTemplate.postForEntity(getBaseUrl() + "/offer/create", dto, Offer.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(OfferStatus.CREATED, response.getBody().getStatus());
    }
}

