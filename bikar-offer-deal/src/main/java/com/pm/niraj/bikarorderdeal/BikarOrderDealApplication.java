package com.pm.niraj.bikarorderdeal;

import com.pm.niraj.sharedlib.SharedLibWebConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SharedLibWebConfig.class)
public class BikarOrderDealApplication {

    public static void main(String[] args) {
        SpringApplication.run(BikarOrderDealApplication.class, args);
    }

}
