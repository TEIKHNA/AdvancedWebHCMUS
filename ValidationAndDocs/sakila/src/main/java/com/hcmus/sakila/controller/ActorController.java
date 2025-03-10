package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.ActorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/actor")
public class ActorController {

    private final ActorService actorService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<ActorDto>>> getActorsList() {
        ResponseDto<List<ActorDto>> response = actorService.getActorsList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteAnActor(@PathVariable Integer id) {
        ResponseDto<?> response = actorService.deleteAnActor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> updateAnActor(@PathVariable Integer id, @RequestBody ActorDto actorDto) {
        ResponseDto<ActorDto> response = actorService.updateAnActor(id, actorDto);
        if (response.getData() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> getActorDetail(@PathVariable Integer id) {
        ResponseDto<ActorDto> response = actorService.getActorDetail(id);
        if (response.getData() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<ActorDto>> addActor(@Valid @RequestBody ActorDto actorDto) {
        ResponseDto<ActorDto> response = actorService.addActor(actorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}