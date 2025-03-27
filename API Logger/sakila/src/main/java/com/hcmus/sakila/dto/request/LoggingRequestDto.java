package com.hcmus.sakila.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoggingRequestDto {

    @Schema(description = "Id of a log")
    String id;

    @Schema(description = "HTTP status of a response", example = "success")
    String statusCategory;

    @Schema(description = "Custom status of a response", example = "SUCCESS")
    String statusState;

    @Schema(description = "HTTP method of a request", example = "GET")
    String method;

    @Schema(description = "Level of a log", example = "INFO")
    String logLevel;

    @Schema(description = "URI of an api", example = "api/actors")
    String uri;
}
