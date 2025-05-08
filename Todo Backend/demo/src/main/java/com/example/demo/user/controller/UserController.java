package com.example.demo.user.controller;


import com.example.demo.user.dto.UserResponseDto;
import com.example.demo.user.service.JwtService;
import com.example.demo.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final JwtService jwtService;

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserResponseDto> getUser(@RequestHeader("Authorization") String authorizationHeader) {
        UUID userId = jwtService.extractUserId(authorizationHeader);

        UserResponseDto responseDto = userService.getUserByUserId(userId);

        return ResponseEntity.ok(responseDto);
    }
}
