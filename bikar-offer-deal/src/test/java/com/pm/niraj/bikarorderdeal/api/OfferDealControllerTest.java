package com.pm.niraj.bikarorderdeal.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferDealControllerTest {
    @Autowired
    public TestRestTemplate restTemplate;

    @Value("${local.server.port}")
    private int port;

    private final String BASE_URL = "http://localhost:";

    @Test
    public void test_AnyRuntimeException_InvokesControllerAdvice(){
        ResponseEntity<String> response = restTemplate.getForEntity(BASE_URL+port+"/offer/get/-1", String.class);
        Assertions.assertEquals("invalid offer id", response.getBody());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
