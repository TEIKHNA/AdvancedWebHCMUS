package com.hcmus.sakila.exception;

import com.hcmus.sakila.dto.response.ApiResponseDto;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<?>> handleNotValidException(MethodArgumentNotValidException e) {
        List<String> messages = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ApiResponseDto<?> response = ApiResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .generalMessage("Validation error!")
                .errorDetails(messages)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponseDto<?>> handleBadCredentialsException(BadCredentialsException e) {
        ApiResponseDto<?> response = ApiResponseDto.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .generalMessage("Bad credentials exception!")
                .errorDetails(List.of(e.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponseDto<?>> handleBadCredentialsException(UsernameNotFoundException e) {
        ApiResponseDto<?> response = ApiResponseDto.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .generalMessage("Username not found!")
                .errorDetails(List.of(e.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponseDto<?>> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiResponseDto<?> response = ApiResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .generalMessage("Parameter not match the field type")
                .errorDetails(List.of("The field sent is not suitable: " + e.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<?>> handleException(Exception e) {
        e.printStackTrace();
        ApiResponseDto<?> response = ApiResponseDto.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .generalMessage("Error at the server!")
                .errorDetails(List.of(e.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }
}
