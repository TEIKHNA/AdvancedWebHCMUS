package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.request.LoginRequestDto;
import com.hcmus.sakila.dto.request.RefreshTokenRequestDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.LoginResponseDto;
import com.hcmus.sakila.dto.response.RefreshTokenResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.ActorService;
import com.hcmus.sakila.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(tags = "[5th Week] Security API - Server A", summary = "Login account",
            description = "Login with account to get token.")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto)  {
        ResponseDto<LoginResponseDto> responseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}