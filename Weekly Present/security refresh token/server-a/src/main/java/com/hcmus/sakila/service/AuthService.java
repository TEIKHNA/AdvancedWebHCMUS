package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.*;
import com.hcmus.sakila.dto.response.*;

import java.util.List;

public interface AuthService {

    ApiResponseDto<LoginResponseDto> login(LoginRequestDto loginRequestDto);

    ApiResponseDto<?> register(RegisterRequestDto registerRequestDto);
}
