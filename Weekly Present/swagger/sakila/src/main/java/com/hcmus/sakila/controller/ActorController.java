package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.request.ActorCreateDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(tags = "Actor Service", summary = "get actors list", description = "get actors list")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content()),
    })
    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<ActorDto>>> getActorsList() {
        ResponseDto<List<ActorDto>> response = actorService.getActorsList();

        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Actor Detail Service", summary = "get actor info", description = "get actor info by id")
    @GetMapping("/{id}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
        @ApiResponse(responseCode = "400", description = "BAD REQUEST", content = @Content()),
    })
    public ResponseEntity<ResponseDto<ActorDto>> getActorDetail(@Parameter(description = "actor id") @PathVariable Integer id) {
        ResponseDto<ActorDto> response = actorService.getActorDetail(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<ActorDto>> addAnActor(
            @Valid @RequestBody ActorCreateDto actorCreateDto) {
        ResponseDto<ActorDto> response = actorService.addActor(actorCreateDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> updateAnActor(@PathVariable Integer id, @Valid @RequestBody ActorUpdateDto actorUpdateDto) {
        ResponseDto<ActorDto> response = actorService.updateActor(id, actorUpdateDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteAnActor(@PathVariable Integer id) {
        ResponseDto<?> response = actorService.deleteAnActor(id);
        return ResponseEntity.ok(response);
    }
}