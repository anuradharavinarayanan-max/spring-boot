package com.example.springboot.controllers.user;

import com.example.springboot.auth.CustomUserDetails;
import com.example.springboot.dtos.AuthResponse;
import com.example.springboot.dtos.LoginRequest;
import com.example.springboot.dtos.RefreshTokenRequest;
import com.example.springboot.entities.RefreshToken;
import com.example.springboot.entities.User;
import com.example.springboot.services.JwtService;
import com.example.springboot.services.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthenticationManager authManager,
                          JwtService jwtService,
                          RefreshTokenService refreshTokenService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword())
        );

        CustomUserDetails userDetails =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userDetails.getUser();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = refreshTokenService.create(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest request) {

        RefreshToken token = refreshTokenService.verify(request.getRefreshToken());

        User user = token.getUser();
        String newAccessToken = jwtService.generateAccessToken(user);

        return new AuthResponse(newAccessToken, token.getToken());
    }
}
