package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.LoggingRequestDto;
import com.hcmus.sakila.dto.response.LoggingResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.dto.response.Status;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoggingFilterServiceImpl implements LoggingFilterService {

    private static final String LOG_FILE = "./logs/api-logs.log";

    List<LoggingResponseDto> loggingResponseDtos = new ArrayList<>();


    @Override
    public ResponseDto<List<LoggingResponseDto>> getLogs(LoggingRequestDto loggingRequestDto){
        loggingResponseDtos.clear();

        try {
            List<String> lines = Files.readAllLines(Paths.get(LOG_FILE));
            LoggingResponseDto loggingResponseDto = new LoggingResponseDto();

            Boolean isRequest = false;
            Boolean isResponse = false;

            for (String line : lines) {
                if(line.contains("Incoming Request")) {

                    if (isResponse) {
                        loggingResponseDtos.add(new LoggingResponseDto(loggingResponseDto.getRequest(), loggingResponseDto.getResponse()));
                    }

                    isRequest = true;
                    isResponse = false;
                    loggingResponseDto.setRequest(line);
                } else if(line.contains("Outgoing Response")) {

                    isResponse = true;
                    isRequest = false;

                    loggingResponseDto.setResponse(line);
                } else if (isRequest) {
                    loggingResponseDto.setRequest(loggingResponseDto.getRequest() + line);
                } else if (isResponse) {
                    loggingResponseDto.setResponse(loggingResponseDto.getResponse() + line);
                }
            }

            if(loggingResponseDto.getResponse() != null &&
                    !loggingResponseDto.getResponse().isEmpty() &&
                    loggingResponseDto.getRequest() != null &&
                    !loggingResponseDto.getRequest().isEmpty()) {
                loggingResponseDtos.add(new LoggingResponseDto(loggingResponseDto.getRequest(), loggingResponseDto.getResponse()));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        getLogsById(loggingRequestDto);

        getLogsByStatusCategory(loggingRequestDto);

        getLogsByStatusState(loggingRequestDto);

        getLogsByMethod(loggingRequestDto);

        getLogsByLogLevel(loggingRequestDto);

        getLogsByUri(loggingRequestDto);

        return new ResponseDto<>(Status.SUCCESS, loggingResponseDtos, null);
    }

    private void getLogsById(LoggingRequestDto loggingRequestDto){

        if (loggingRequestDto.getId() == null) {
            return;
        }

        for (LoggingResponseDto logging : loggingResponseDtos) {
            if(logging.getRequest()
                    .contains(loggingRequestDto.getId())) {
                loggingResponseDtos = List.of(logging);
                return;
            }
        }

        loggingResponseDtos = List.of();
    }

    private void getLogsByStatusCategory(LoggingRequestDto loggingRequestDto){

        if (loggingRequestDto.getStatusCategory() == null) {
            return;
        }

        int minStatus, maxStatus;

        switch (loggingRequestDto.getStatusCategory()) {
            case "information": minStatus = 100; maxStatus = 199; break;
            case "success": minStatus = 200; maxStatus = 299; break;
            case "redirection": minStatus = 300; maxStatus = 399; break;
            case "client-error": minStatus = 400; maxStatus = 499; break;
            case "server-error": minStatus = 500; maxStatus = 599; break;
            default: throw new IllegalArgumentException("Invalid status category");
        }

        List<LoggingResponseDto> loggings = new ArrayList<>();

        for (LoggingResponseDto logging : loggingResponseDtos) {
            int status = extractStatusCode(logging.getResponse());
            if (status >= minStatus && status <= maxStatus) {
                loggings.add(logging);
            }
        }

        loggingResponseDtos = loggings;
    }

    private void getLogsByStatusState(LoggingRequestDto loggingRequestDto){

        if (loggingRequestDto.getStatusState() == null) {
            return;
        }

        List<LoggingResponseDto> loggings = new ArrayList<>();

        for (LoggingResponseDto logging : loggingResponseDtos) {
            if (logging.getResponse()
                    .contains("Status: " + loggingRequestDto.getStatusState())) {
                loggings.add(logging);
            }
        }

        loggingResponseDtos = loggings;
    }

    private void getLogsByMethod(LoggingRequestDto loggingRequestDto){

        if (loggingRequestDto.getMethod() == null) {
            return;
        }

        List<LoggingResponseDto> loggings = new ArrayList<>();

        for (LoggingResponseDto logging : loggingResponseDtos) {
            if (logging.getRequest()
                    .contains("Incoming Request: " + loggingRequestDto.getMethod())) {
                loggings.add(logging);
            }
        }

        loggingResponseDtos = loggings;
    }

    private void getLogsByLogLevel (LoggingRequestDto loggingRequestDto){

        if (loggingRequestDto.getLogLevel() == null) {
            return;
        }

        List<LoggingResponseDto> loggings = new ArrayList<>();

        for (LoggingResponseDto logging : loggingResponseDtos) {
            if (logging.getResponse()
                    .contains(loggingRequestDto.getLogLevel())) {
                loggings.add(logging);
            }
        }

        loggingResponseDtos = loggings;
    }

    private void getLogsByUri (LoggingRequestDto loggingRequestDto){

        if (loggingRequestDto.getUri() == null) {
            return;
        }

        List<LoggingResponseDto> loggings = new ArrayList<>();

        for (LoggingResponseDto logging : loggingResponseDtos) {
            if (logging.getRequest()
                    .contains(loggingRequestDto.getUri() + " |")) {
                loggings.add(logging);
            }
        }

        loggingResponseDtos = loggings;
    }

    private int extractStatusCode(String logLine) {
        try {
            return Integer.parseInt(logLine.replaceAll(".*Status Code: (\\d+).*", "$1"));
        } catch (Exception e) {
            return -1; // Return -1 if parsing fails
        }
    }
}
