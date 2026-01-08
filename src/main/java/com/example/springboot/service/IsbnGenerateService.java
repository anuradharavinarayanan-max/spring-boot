package com.example.springboot.service;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class IsbnGenerateService {
    private static final String CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private final SecureRandom random = new SecureRandom();

    public String generateCode() {
        return random.ints(8, 0, CHARS.length())
                .mapToObj(CHARS::charAt)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append)
                .toString();
    }
}
