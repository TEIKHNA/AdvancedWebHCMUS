package com.hcmus.sakila.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDto<T> {

    @Schema(description = "Status of the response", example = "200 | 400 | 401 | 403 | 404 | 500")
    private Integer statusCode;

    @Schema(description = "General message", example = "Get data successfully!")
    private String generalMessage;

    @Schema(description = "Data of the response")
    private T data;

    @Schema(description = "Error details of response", example = "[ \"There are some errors or fail ...\" ]")
    private List<String> errorDetails;

    @Schema(description = "Timestamp of the response", example = "2023-10-01 ~ 12:00:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd' ~ 'HH:mm:ss")
    private LocalDateTime timestamp;
}
