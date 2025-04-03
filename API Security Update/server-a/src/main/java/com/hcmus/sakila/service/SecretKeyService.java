package com.hcmus.sakila.service;

public interface SecretKeyService {

    String hash(String path, String time);
}
