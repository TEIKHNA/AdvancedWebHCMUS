package com.hcmus.actor.controller;

import com.hcmus.actor.dto.ActorDto;
import com.hcmus.actor.dto.ActorDtoRequest;
import com.hcmus.actor.dto.ResponseDto;
import com.hcmus.actor.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto> deleteAActor(@PathVariable Integer id) {
        ResponseDto response = actorService.deleteAActor(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto> updateAActor(@PathVariable Integer id, @RequestBody ActorDtoRequest actorDtoRequest) {
        ResponseDto response = actorService.updateAActor(id, actorDtoRequest);
        return ResponseEntity.ok(response);
    }
}