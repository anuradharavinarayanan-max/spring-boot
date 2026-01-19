package com.example.springboot.services;

import com.example.springboot.entities.RefreshToken;
import com.example.springboot.entities.User;
import com.example.springboot.repositories.RefreshTokenRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private static final long REFRESH_TOKEN_VALIDITY = 7 * 24 * 60 * 60; // 7 days

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // Create & store refresh token
    public String create(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(REFRESH_TOKEN_VALIDITY));

        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    // Validate refresh token
    public RefreshToken verify(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }

    // Optional: delete tokens on logout
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }
}

