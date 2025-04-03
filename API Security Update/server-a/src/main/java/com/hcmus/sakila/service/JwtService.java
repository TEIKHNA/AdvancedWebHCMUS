package com.hcmus.sakila.service;

import com.hcmus.sakila.model.Account;

public interface JwtService {

    String generateAccessToken(Account account);

    boolean validateAccessToken(String token);

    String extractRole(String token);

    String extractUsername(String token);
}
