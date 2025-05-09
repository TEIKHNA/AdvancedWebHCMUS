package com.example.demo.user.service;

import com.example.demo.user.domain.User;

import java.util.UUID;

public interface JwtService {

    String generateAccessToken(User user);

    boolean validateAccessToken(String token);

    String extractRole(String token);

    String extractUsername(String token);

    UUID extractUserId(String authHeader);
}
