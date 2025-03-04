package com.hcmus.actor.controller;

import com.hcmus.actor.domain.Actor;
import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.RequestDto;
import com.hcmus.actor.dto.ResponseDto;
import com.hcmus.actor.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/actor")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/list")
    public ResponseEntity<ResponseDto<List<ActorDto>>> getActorsList() {
        ResponseDto<List<ActorDto>> response = actorService.getActorsList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/add")
    public ResponseEntity<ResponseDto<ActorDto>> addActor(
            @RequestBody RequestDto requestDto
    ) {
        Actor actor = new Actor(null, requestDto.getFirstName(), requestDto.getLastName(), null);
        ResponseDto<ActorDto> response = actorService.addActor(actor);
        return ResponseEntity.ok(response);
    }
}