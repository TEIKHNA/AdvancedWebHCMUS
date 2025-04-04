package com.hcmus.sakila.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

@NoArgsConstructor
@Component
public class SecretKeyInterceptor implements HandlerInterceptor {

    @Value("${security.secret-key}")
    private String securitySecretKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("X-Secret-Token");
        String time = request.getHeader("X-Time");
        if (token == null || time == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        Instant expiredTime = Instant.parse(time).plus(1, ChronoUnit.MINUTES);
        if (expiredTime.isBefore(Instant.now())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String apiPath = request.getRequestURI();
        String expectedToken = null;
        expectedToken = hash(apiPath, time, securitySecretKey);

        if (!expectedToken.equals(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private String hash(String apiPath, String time, String secretKey) {
        try {
            String data = apiPath + time + secretKey;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing", e);
        }
    }
}
