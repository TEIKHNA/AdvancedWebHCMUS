package com.hcmus.sakila.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class ActorUpdateDto {

    @Schema(description = "First name of an actor", example = "An")
    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "First name must contain only letters and spaces")
    String firstName;

    @Schema(description = "Last name of an actor", example = "Nguyen")
    @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Last name must contain only letters and spaces")
    String lastName;
}