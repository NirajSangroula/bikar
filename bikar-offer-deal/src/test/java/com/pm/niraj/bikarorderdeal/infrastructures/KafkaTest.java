package com.pm.niraj.bikarorderdeal.infrastructures;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Test
    public void test__canPublishMessage() throws ExecutionException, InterruptedException {
        kafkaTemplate.send("mytopic", "Hello");
        /**
         * Tested with command
         * docker exec -it kafka bash
         * kafka-console-consumer --bootstrap-server localhost:9092 --topic mytopic --from-beginning
         *
         * We need @Listener to actually test it through code so
         */

    }
}
