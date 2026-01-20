package com.example.springboot.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class OpenLibraryServiceHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // replace with real check
        boolean up = true;

        if (up) {
            return Health.up()
                    .withDetail("OpenLibraryService", "available")
                    .build();
        }

        return Health.down()
                .withDetail("OpenLibraryService", "unavailable")
                .build();
    }
}