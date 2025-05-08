package com.example.demo.user.service;

import com.example.demo.user.dto.*;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    RegistrationResponseDto register(RegistrationRequestDto registrationRequestDto);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);
}
