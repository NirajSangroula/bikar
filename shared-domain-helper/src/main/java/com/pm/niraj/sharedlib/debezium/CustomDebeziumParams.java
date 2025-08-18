package com.pm.niraj.sharedlib.debezium;


public interface CustomDebeziumParams {
    void handleChangeEvent(String key, String value);
}
