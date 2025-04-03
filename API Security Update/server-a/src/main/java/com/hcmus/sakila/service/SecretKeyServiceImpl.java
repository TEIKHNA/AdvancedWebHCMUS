package com.hcmus.sakila.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class SecretKeyServiceImpl implements SecretKeyService {

    @Value("${security.secret-key}")
    private String securitySecretKey;

    @Override
    public String hash(String apiPath, String time) {
        try {
            String data = apiPath + time + securitySecretKey;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing", e);
        }
    }
}
