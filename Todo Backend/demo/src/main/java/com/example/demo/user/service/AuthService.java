package com.example.demo.user.service;

import com.example.demo.user.dto.*;

import java.util.UUID;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    RegistrationResponseDto register(RegistrationRequestDto registrationRequestDto);

    RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto);

    LogoutResponse logout(UUID userId);
}
