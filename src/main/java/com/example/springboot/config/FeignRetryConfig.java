package com.example.springboot.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignRetryConfig {

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(
                1000,   // initial interval (1s)
                3000,   // max interval (3s)
                3       // max attempts
        );
    }
}

