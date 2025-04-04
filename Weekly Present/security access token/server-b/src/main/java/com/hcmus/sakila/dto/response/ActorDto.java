package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.model.Actor;
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

    @Schema(description = "Id of an actor", example = "1")
    private Integer id;

    @Schema(description = "First name of an actor", example = "An")
    private String firstName;

    @Schema(description = "Last name of an actor", example = "Nguyen")
    private String lastName;

    @Schema(description = "Last update date", example = "2018-12-30T19:34:50.63Z")
    private Instant lastUpdate;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.lastUpdate = actor.getLastUpdate();
    }
}