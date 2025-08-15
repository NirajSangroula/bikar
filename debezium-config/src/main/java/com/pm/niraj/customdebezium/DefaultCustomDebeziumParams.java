package com.pm.niraj.customdebezium;

import com.pm.niraj.sharedlib.debezium.CustomDebeziumParams;
import org.springframework.stereotype.Component;

@Component
public class DefaultCustomDebeziumParams implements CustomDebeziumParams {
    @Override
    public void handleChangeEvent(String key, String value) {
        System.out.println("key: " + key + " value: " + value + " CDC event...");
    }
}
