package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Account;
import com.hcmus.sakila.dto.request.LoginRequestDto;
import com.hcmus.sakila.dto.response.LoginResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.dto.response.Status;
import com.hcmus.sakila.repository.AccountRepository;
import com.hcmus.sakila.repository.ActorRepository;
import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

    private final JwtService jwtService;

    @Override
    public ResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        // Encode password
        Account account = accountRepository.findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (account == null) {
            return new ResponseDto<>(Status.FAIL, null, null);
        }
        String accessToken = null;
        try {
            accessToken = jwtService.generateToken(account.getUsername());
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken);
        return new ResponseDto<>(Status.SUCCESS, loginResponseDto, null);
    }
}
