package com.example.springboot.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class BookServiceHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // replace with real check
        boolean up = true;

        if (up) {
            return Health.up()
                    .withDetail("bookService", "available")
                    .build();
        }

        return Health.down()
                .withDetail("bookService", "unavailable")
                .build();
    }
}
