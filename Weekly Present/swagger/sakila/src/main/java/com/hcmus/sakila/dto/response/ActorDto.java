package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.Actor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ActorDto {

    @Schema(description = "Actor ID", example = "1")
    private Integer id;

    @Schema(description = "First name of the actor", example = "John")
    private String firstName;

    private String lastName;

    private Instant lastUpdate;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.lastUpdate = actor.getLastUpdate();
    }
}