package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.request.LoggingRequestDto;
import com.hcmus.sakila.dto.response.LoggingResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.LoggingFilterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@RequestMapping("/api/logs")
public class LoggingFilterController {

    private final LoggingFilterService loggingFilterService;

    @GetMapping("")
    public ResponseEntity<ResponseDto<List<LoggingResponseDto>>> getLogs(@RequestBody LoggingRequestDto loggingRequestDto) throws IOException {
        return ResponseEntity.ok(loggingFilterService.getLogs(loggingRequestDto));
    }
}
