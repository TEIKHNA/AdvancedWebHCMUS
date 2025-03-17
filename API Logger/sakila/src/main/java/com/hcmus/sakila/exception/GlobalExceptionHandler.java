package com.hcmus.sakila.exception;

import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.dto.response.Status;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<?>> handleNotValidException(MethodArgumentNotValidException e) {
        List<String> messages = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ResponseDto<?> response = ResponseDto.builder()
                .status(Status.FAIL)
                .data(null)
                .messages(messages)
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ResponseDto<?>> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {
        List<String> messages = List.of("The field sent is not suitable: " + e.getMessage());
        ResponseDto<?> response = ResponseDto.builder()
                .status(Status.FAIL)
                .data(null)
                .messages(messages)
                .build();
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<?>> handleException(Exception e) {
        e.printStackTrace();
        List<String> messages = List.of(e.getMessage());
        ResponseDto<?> response = ResponseDto.builder()
                .status(Status.FAIL)
                .data(null)
                .messages(messages)
                .build();
        return ResponseEntity.ok(response);
    }
}
