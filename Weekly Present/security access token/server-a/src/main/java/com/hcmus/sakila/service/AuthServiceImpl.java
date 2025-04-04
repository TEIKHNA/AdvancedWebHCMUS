package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.LoginRequestDto;
import com.hcmus.sakila.dto.request.RegisterRequestDto;
import com.hcmus.sakila.dto.response.ApiResponseDto;
import com.hcmus.sakila.dto.response.LoginResponseDto;
import com.hcmus.sakila.model.Account;
import com.hcmus.sakila.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Override
    public ApiResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);

            if (authentication.isAuthenticated()) {
                Account account = accountRepository.findByUsername(username).orElse(null);
                String accessToken = jwtService.generateAccessToken(account);
                LoginResponseDto responseDto = new LoginResponseDto();
                responseDto.setAccessToken(accessToken);

                return ApiResponseDto.<LoginResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .generalMessage("Login successfully!")
                        .data(responseDto)
                        .timestamp(LocalDateTime.now())
                        .build();
            }
        } catch (RuntimeException e) {
            log.info("Error login " + e.getMessage());
        }
        throw new BadCredentialsException("Invalid username or password");
    }

    @Override
    public ApiResponseDto<?> register(RegisterRequestDto registerRequestDto) {
        if (accountRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new BadCredentialsException(String.format("Username %s already exists", registerRequestDto.getUsername()));
        }

        Account account = new Account();
        account.setUsername(registerRequestDto.getUsername());
        account.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));

        accountRepository.save(account);
        return ApiResponseDto.builder()
                .statusCode(HttpStatus.CREATED.value())
                .generalMessage("Account created successfully!")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
