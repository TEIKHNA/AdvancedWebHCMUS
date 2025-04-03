package com.hcmus.sakila.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequestDto {

    @Schema(description = "Username of the account", example = "admin")
    @NotNull(message = "Username required")
    @NotBlank(message = "Username not blank")
    String username;

    @Schema(description = "Password of the account", example = "123")
    @NotNull(message = "Password required")
    @NotBlank(message = "Password not blank")
    String password;
}