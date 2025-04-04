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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    @Override
    public ApiResponseDto<?> login(LoginRequestDto loginRequestDto) {

        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        Account account = accountRepository.findByUsername(username)
                .orElse(null);

        // check password

        if (account == null && !account.getPassword().equals(password)) {// && password not valid
            return ApiResponseDto.<LoginResponseDto>builder()
                    .statusCode(HttpStatus.UNAUTHORIZED.value())
                    .generalMessage("Username or password is incorrect!")
                    .timestamp(LocalDateTime.now())
                    .build();
        }

        return ApiResponseDto.<LoginResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .generalMessage("Login successfully!")
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public ApiResponseDto<?> register(RegisterRequestDto registerRequestDto) {
        if (accountRepository.existsByUsername(registerRequestDto.getUsername())) {
            throw new RuntimeException(String.format("Username %s already exists", registerRequestDto.getUsername()));
        }

        Account account = new Account();
        account.setUsername(registerRequestDto.getUsername());
        account.setPassword(registerRequestDto.getPassword());

        accountRepository.save(account);
        return ApiResponseDto.builder()
                .statusCode(HttpStatus.CREATED.value())
                .generalMessage("Account created successfully!")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
