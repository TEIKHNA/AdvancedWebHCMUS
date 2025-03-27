package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface SecretKeyService {

    String generateToken(String requestUrl, String time);
}
