package com.hcmus.sakila.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActorUpdateDto {

    @Schema(description = "First name of an actor", example = "An")
    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    String firstName;

    @Schema(description = "Last name of an actor", example = "Nguyen")
    @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters")
    String lastName;
}