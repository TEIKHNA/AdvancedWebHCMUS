package com.hcmus.sakila.service;

import com.nimbusds.jose.JOSEException;

public interface JwtService {

    String generateToken(String username) throws JOSEException;

    boolean validateToken(String token);
}
