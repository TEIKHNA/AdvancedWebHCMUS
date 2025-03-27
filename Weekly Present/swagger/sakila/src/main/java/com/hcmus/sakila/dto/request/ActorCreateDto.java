package com.hcmus.sakila.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActorCreateDto {

    @NotNull(message = "First name is required")
    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name must contain only letters and spaces")
    String firstName;

    @NotNull(message = "Last name is required")
    @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name must contain only letters and spaces")
    String lastName;
}