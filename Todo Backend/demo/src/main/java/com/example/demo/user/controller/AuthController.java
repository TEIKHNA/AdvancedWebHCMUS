package com.example.demo.user.controller;

import com.example.demo.user.dto.*;
import com.example.demo.user.service.AuthService;
import com.example.demo.user.service.JwtService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto)  {
        LoginResponseDto responseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponseDto> login(@Valid @RequestBody RegistrationRequestDto registerRequestDto)  {
        RegistrationResponseDto responseDto = authService.register(registerRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<RefreshTokenResponseDto> refreshToken(@Valid @RequestBody RefreshTokenRequestDto refreshTokenRequestDto)  {
        RefreshTokenResponseDto responseDto = authService.refreshToken(refreshTokenRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponse> logout(@RequestHeader("Authorization") String authorizationHeader) {

        UUID userId = jwtService.extractUserId(authorizationHeader);

        return ResponseEntity.ok(authService.logout(userId));
    }
}