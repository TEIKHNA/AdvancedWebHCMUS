package com.hcmus.sakila.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequestDto {

    @Schema(description = "Username of the account", example = "admin")
    @NotNull(message = "Username required")
    @NotBlank(message = "Username not blank")
    String username;

    @Schema(description = "Password of the account", example = "1")
    @NotNull(message = "Password required")
    @NotBlank(message = "Password not blank")
    String password;
}