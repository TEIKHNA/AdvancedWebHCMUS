package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.request.LoginRequestDto;
import com.hcmus.sakila.dto.request.RefreshTokenRequestDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.LoginResponseDto;
import com.hcmus.sakila.dto.response.RefreshTokenResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface AuthService {

    ResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto);

//    ResponseDto<RefreshTokenResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
