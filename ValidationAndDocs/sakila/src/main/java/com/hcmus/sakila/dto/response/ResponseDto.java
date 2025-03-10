package com.hcmus.sakila.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> {

    private T data;
    @Schema(description = "Response message", example = "Success ...")
    private String message;
}
