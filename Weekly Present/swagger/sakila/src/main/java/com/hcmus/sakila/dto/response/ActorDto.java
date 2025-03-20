package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.Actor;
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

    private Integer id;

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