package com.example.demo.user.service;

import com.example.demo.user.domain.RefreshToken;
import com.example.demo.user.domain.User;
import com.example.demo.user.dto.*;
import com.example.demo.user.repository.RefreshTokenRepository;
import com.example.demo.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final RefreshTokenRepository refreshTokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authToken);

        if (authentication.isAuthenticated()) {
            User user = userRepository.findByUsername(username);
            String accessToken = jwtService.generateAccessToken(user);
            String refreshToken = getRefreshToken(user.getUserId());

            return new LoginResponseDto(accessToken, refreshToken);
        }
        
        throw new BadCredentialsException("Invalid username or password");
    }

    @Override
    public RegistrationResponseDto register(RegistrationRequestDto registrationRequestDto) {
        if (userRepository.existsByUsername(registrationRequestDto.getUsername())) {
            throw new BadCredentialsException(String.format("Username %s already exists", registrationRequestDto.getUsername()));
        }

        User user = new User();
        user.setUsername(registrationRequestDto.getUsername());
        user.setHashPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));

        userRepository.save(user);
        return new RegistrationResponseDto("User has been created successfully!");
    }

    @Override
    public RefreshTokenResponseDto refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
        String refreshTokenStr = refreshTokenRequestDto.getRefreshToken();
        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenStr);

        if (refreshToken == null || refreshToken.getValidUntil().isBefore(Instant.now())) {
            throw new BadCredentialsException("Invalid refresh token");
        }

        String accessToken = jwtService.generateAccessToken(refreshToken.getUser());

        return new RefreshTokenResponseDto(accessToken);
    }

    private String getRefreshToken(UUID userId) {
            User user = userRepository.findByUserId(userId);
            if (user == null) {
                throw new BadCredentialsException("user not found!");
            }

            RefreshToken refreshToken = refreshTokenRepository.findByUser(user);
            if (refreshToken == null) {
                refreshToken = new RefreshToken();
                refreshToken.setUser(user);
            }
            String uuid = UUID.randomUUID().toString();
            refreshToken.setToken(uuid);
            refreshToken.setValidUntil(Instant.now().plus(30, ChronoUnit.DAYS));
            return refreshTokenRepository.save(refreshToken).getToken();
    }
}
