//package com.hcmus.sakila.service;
//
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jose.crypto.MACVerifier;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//
//@Service
//public class SecretKeyServiceImpl implements SecretKeyService {
//
//    @Value("${secret.key}")
//    private String SECRET_KEY;
//
//    @Override
//    public String generateToken(String requestUrl, String time) {
//        String data = requestUrl + time + SECRET_KEY;
//        MessageDigest md = null;
//        try {
//            md = MessageDigest.getInstance("SHA-256");
//        } catch (NoSuchAlgorithmException e) {
//            throw new RuntimeException(e);
//        }
//        byte[] hash = md.digest(data.getBytes(StandardCharsets.UTF_8));
//        StringBuilder hexString = new StringBuilder();
//        for (byte b : hash) {
//            hexString.append(String.format("%02x", b));
//        }
//        return hexString.toString();
//    }
//}
