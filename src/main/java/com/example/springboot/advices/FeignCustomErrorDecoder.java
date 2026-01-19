package com.example.springboot.advices;

import com.example.springboot.exceptions.BookNotFoundException;
import com.example.springboot.exceptions.ExternalServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        return switch (response.status()) {
            case 404 -> new BookNotFoundException("Resource not found");
            case 400 -> new IllegalArgumentException("Bad request");
            case 500 -> new ExternalServiceException("External service error");
            default -> new RuntimeException("Unexpected error: " + response.status());
        };
    }
}

