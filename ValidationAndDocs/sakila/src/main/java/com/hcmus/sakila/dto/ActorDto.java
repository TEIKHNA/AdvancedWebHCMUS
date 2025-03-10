package com.hcmus.sakila.dto;

import com.hcmus.sakila.domain.Actor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActorDto {
    private Integer id;
    @NotBlank(message = "First name is required")
    @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
    private String firstName;
    @NotBlank(message = "Last name is required")
    @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters")
    private String lastName;
    private Instant lastUpdate;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.lastUpdate = actor.getLastUpdate();
    }
}