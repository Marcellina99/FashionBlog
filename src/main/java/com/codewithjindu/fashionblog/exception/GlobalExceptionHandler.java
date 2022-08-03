package com.codewithjindu.fashionblog.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Configuration
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, ?>> handleExceptions(Exception exception){
        return ResponseEntity.badRequest().body(
                Map.of("Error", true,
                        "Message", exception.getMessage())
        );
    }
}
