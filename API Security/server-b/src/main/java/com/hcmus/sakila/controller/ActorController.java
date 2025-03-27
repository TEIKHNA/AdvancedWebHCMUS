package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    @Operation(tags = "Actor Service", summary = "Retrieve actors list",
            description = "Retrieve a list of all available actors.")
    @GetMapping
    public ResponseEntity<ResponseDto<List<ActorDto>>> getActorsList() {
        ResponseDto<List<ActorDto>> response = actorService.getActorsList();

        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Actor Service", summary = "Retrieve actor detail",
            description = "Retrieve detail of an actor by id.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> getActorDetail(@PathVariable Integer id) {
        ResponseDto<ActorDto> response = actorService.getActorDetail(id);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Actor Service", summary = "Add an actor",
            description = "Add an actor to the database.")
    @PostMapping
    public ResponseEntity<ResponseDto<ActorDto>> addAnActor(
            @Valid @RequestBody ActorCreateDto actorCreateDto) {
        ResponseDto<ActorDto> response = actorService.addActor(actorCreateDto);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Actor Service", summary = "Update an actor",
            description = "Update an actor in the database.")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> updateAnActor(@PathVariable Integer id, @Valid @RequestBody ActorUpdateDto actorUpdateDto) {
        ResponseDto<ActorDto> response = actorService.updateActor(id, actorUpdateDto);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Actor Service", summary = "Delete an actor",
            description = "Delete an actor in the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteAnActor(@PathVariable Integer id) {
        ResponseDto<?> response = actorService.deleteAnActor(id);
        return ResponseEntity.ok(response);
    }
}