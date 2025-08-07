package com.pm.niraj.bikarorderdeal.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice(annotations =  RestController.class)
public class OrderControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    ResponseEntity<String> handleInvalidOrder(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
