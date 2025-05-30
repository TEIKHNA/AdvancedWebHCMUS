package com.hcmus.sakila.service;

import com.hcmus.sakila.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security-custom.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security-custom.jwt.expiration-time}")
    private String jwtExpiration;

    @Override
    public String generateAccessToken(Account account) {
        return Jwts.builder()
                .setSubject(account.getUsername())
                .claim("role", (String) account.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiration)))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateAccessToken(String token) {
        try {
            final Claims claims = getClaims(token);
            return claims.getExpiration().after(new Date(System.currentTimeMillis()));
        } catch (JwtException e) {
            return false;
        }
    }

    @Override
    public String extractRole(String token) {
        return (String) getClaims(token).getOrDefault("role", "NONE");
    }

    @Override
    public String extractUsername(String token) {
        return getClaims(token).getIssuer();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
