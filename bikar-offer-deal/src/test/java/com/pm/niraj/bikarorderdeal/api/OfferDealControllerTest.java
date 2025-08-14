package com.pm.niraj.bikarorderdeal.api;

import com.pm.niraj.bikarorderdeal.BikarOrderDealApplication;
import com.pm.niraj.bikarorderdeal.domain.entity.Offer;
import static org.junit.jupiter.api.Assertions.*;

import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferStatus;
import com.pm.niraj.bikarorderdeal.domain.entity.enums.OfferType;
import com.pm.niraj.bikarorderdeal.dto.OfferDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"debezium.enabled=false"},
        classes = {BikarOrderDealApplication.class}
)
@ActiveProfiles("test")
public class OfferDealControllerTest {
    @Autowired
    public TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void contextLoads(){
        System.out.println("Port = " + port);
    }

    private final String BASE_URL = "http://localhost:";

//    @Test
    //Test only in @ActiveProfiles("prod")
    public void test_AnyRuntimeException_InvokesControllerAdvice(){
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL+port+"/offer/get/-1", String.class);
        assertEquals("invalid offer id", response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void test__CreatingOffer__SavesOfferInDatabase(){
        OfferDto dto = OfferDto.builder().title("title").description("description")
                .offerType(OfferType.ONE_TIME.toString())
                .providerId(1L).build();
        ResponseEntity<Offer> response = restTemplate.postForEntity(BASE_URL+port+"/offer/create",dto,  Offer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(dto.getTitle(), response.getBody().getTitle());
        assertEquals(dto.getDescription(), response.getBody().getDescription());
        assertTrue(response.getBody().getId() > 0);
        assertTrue(response.getBody().getProvider().getId() > 0);
    }

    @Test
    public void test__CreatingOffer__CreatesCorrectStatus(){
        OfferDto dto = OfferDto.builder().title("title").description("description")
                .offerType(OfferType.ONE_TIME.toString())
                .providerId(1L).build();
        ResponseEntity<Offer> response = restTemplate.postForEntity(BASE_URL+port+"/offer/create",dto,  Offer.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(OfferStatus.CREATED, response.getBody().getStatus());
    }

}
