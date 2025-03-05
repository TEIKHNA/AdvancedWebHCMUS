package com.hcmus.actor.dto;

import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActorDtoRequest {
    String firstName;
    String lastName;
    Instant lastUpdate;

}