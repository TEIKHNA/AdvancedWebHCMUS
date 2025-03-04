package com.hcmus.actor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link com.hcmus.actor.domain.Actor}
 */
@AllArgsConstructor
@Getter
@Setter
@Value
public class ActorDto implements Serializable {
    Integer id;
    String firstName;
    String lastName;
    Instant lastUpdate;
}