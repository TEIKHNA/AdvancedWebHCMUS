package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Account;
import com.hcmus.sakila.dto.request.LoginRequestDto;
import com.hcmus.sakila.dto.request.RefreshTokenRequestDto;
import com.hcmus.sakila.dto.response.LoginResponseDto;
import com.hcmus.sakila.dto.response.RefreshTokenResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.dto.response.Status;
import com.hcmus.sakila.repository.AccountRepository;
import com.nimbusds.jose.JOSEException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AccountRepository accountRepository;

//    private final TokenService tokenService;

    @Override
    public ResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        // Encode password
        Account account = accountRepository.findByUsernameAndPassword(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        if (account == null) {
            return new ResponseDto<>(Status.FAIL, null, null);
        }

//        String accessToken = null;
//        try {
//            accessToken = tokenService.generateAccessToken(account.getUsername());
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
//        String refreshToken = tokenService.generateRefreshToken(account.getUsername(), account.getPassword());
//        account.setRefreshToken(refreshToken);
//        accountRepository.save(account);

//        LoginResponseDto loginResponseDto = new LoginResponseDto(accessToken, refreshToken);
        LoginResponseDto loginResponseDto = new LoginResponseDto(true);
        return new ResponseDto<>(Status.SUCCESS, loginResponseDto, null);
    }

//    @Override
//    public ResponseDto<RefreshTokenResponseDto> refreshToken(RefreshTokenRequestDto refreshTokenRequestDto) {
//        String refreshToken = refreshTokenRequestDto.getRefreshToken();
//        Account account = accountRepository.findByRefreshToken(refreshToken);
//        if (account == null) {
//            return new ResponseDto<>(Status.FAIL, null, null);
//        }
//
//        String accessToken = null;
//        try {
//            accessToken = tokenService.generateAccessToken(account.getUsername());
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
//
//        RefreshTokenResponseDto refreshTokenResponseDto = new RefreshTokenResponseDto(accessToken);
//        return new ResponseDto<>(Status.SUCCESS, refreshTokenResponseDto, null);
//    }
}
