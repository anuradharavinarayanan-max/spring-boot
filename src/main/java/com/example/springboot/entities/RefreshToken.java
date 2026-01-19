package com.example.springboot.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @ManyToOne
    private User user;

    private Instant expiryDate;

    public void setExpiryDate(Instant instant) {
        this.expiryDate = instant;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Instant getExpiryDate() {
        return this.getExpiryDate();
    }

    public User getUser() {
        return this.user;
    }
}

