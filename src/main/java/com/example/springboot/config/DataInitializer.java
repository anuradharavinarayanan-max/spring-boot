package com.example.springboot.config;

import com.example.springboot.enums.Role;
import com.example.springboot.service.CustomUserDetailsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CustomUserDetailsService userService;

    public DataInitializer(CustomUserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Econded value" + encoder.encode("user123"));
        //userService.createUser("admin", "admin123", Role.ADMIN);
        //userService.createUser("user", "user123", Role.USER);
    }
}
