package com.hcmus.actor.dto;

import com.hcmus.actor.domain.Actor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@Value
public class ActorDto implements Serializable {
    Integer id;
    String firstName;
    String lastName;
    Instant lastUpdate;

    public ActorDto(Actor actor) {
        this.id = actor.getId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.lastUpdate = actor.getLastUpdate();
    }
}