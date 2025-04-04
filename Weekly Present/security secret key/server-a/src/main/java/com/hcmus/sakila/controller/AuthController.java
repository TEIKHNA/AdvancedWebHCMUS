package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.request.*;
import com.hcmus.sakila.dto.response.*;
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
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(@Valid @RequestBody LoginRequestDto loginRequestDto)  {
        ApiResponseDto<LoginResponseDto> responseDto = authService.login(loginRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(tags = "[5th Week] Security API - Server A", summary = "Register account",
            description = "Register an account to access the system.")
    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<?>> login(@Valid @RequestBody RegisterRequestDto registerRequestDto)  {
        ApiResponseDto<?> responseDto = authService.register(registerRequestDto);
        return ResponseEntity.ok(responseDto);
    }
}