package com.hcmus.sakila.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseDto {

    @Schema(description = "Error messages", example = "[ \"There are some errors ...\" ]")
    private List<String> messages;
}
