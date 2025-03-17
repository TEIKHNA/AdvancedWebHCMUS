package com.hcmus.sakila.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {

    @Enumerated(EnumType.STRING)
    @Schema(description = "Status of the response", example = "SUCCESS | ERROR | FAIL | ...")
    private Status status;

    @Schema(description = "Data of the response")
    private T data;

    @Schema(description = "Messages of response", example = "[ \"There are some errors or fail ...\" ]")
    private List<String> messages;
}
