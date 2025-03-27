package com.hcmus.sakila.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.UriUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@NoArgsConstructor
@Component
public class SecretKeyInterceptor implements HandlerInterceptor {

    @Value("${secret.key}")
    private String SECRET_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("X-Secret-Token");
        String time = request.getHeader("X-Time");

        if (token == null || time == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        String apiPath = UriUtils.decode(request.getRequestURI(), StandardCharsets.UTF_8);
        String expectedToken = null;
        try {
            expectedToken = generateToken(apiPath, time, SECRET_KEY);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (!expectedToken.equals(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private String generateToken(String apiPath, String time, String secretKey) throws NoSuchAlgorithmException {
        String data = apiPath + time + secretKey;
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
