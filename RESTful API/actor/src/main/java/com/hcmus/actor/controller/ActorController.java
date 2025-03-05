package com.hcmus.actor.controller;

import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.ResponseDto;
import com.hcmus.actor.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> getActorDetail(@PathVariable Integer id) {
        ResponseDto<ActorDto> response = actorService.getActorDetail(id);
        return ResponseEntity.ok(response);
    }
}