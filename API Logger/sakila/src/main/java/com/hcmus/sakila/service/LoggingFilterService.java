package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.LoggingRequestDto;
import com.hcmus.sakila.dto.response.LoggingResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface LoggingFilterService {

    ResponseDto<List<LoggingResponseDto>> getLogs(LoggingRequestDto loggingRequestDto);
}
