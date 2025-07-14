package com.pm.niraj.bikarconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BikarConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikarConfigServerApplication.class, args);
    }

}
