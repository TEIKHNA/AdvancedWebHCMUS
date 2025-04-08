package com.hcmus.sakila.controller;

import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.request.ActorAddDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.response.ErrorResponseDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.ActorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/actor")
public class ActorController {

    private final ActorService actorService;

    private final SimpMessagingTemplate simpMessageTemplate;

    @Operation(tags = "Actor Service", summary = "Retrieve actors list",
            description = "Retrieve a list of all available actors.",
            responses = {@ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @GetMapping("")
    public ResponseEntity<ResponseDto<List<ActorDto>>> getActorsList() {
        ResponseDto<List<ActorDto>> response = actorService.getActorsList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Actor Service", summary = "Retrieve actor detail",
            description = "Retrieve detail of an actor by id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> getActorDetail(@PathVariable Integer id) {
        ResponseDto<ActorDto> response = actorService.getActorDetail(id);
        if (response.getData() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Actor Service", summary = "Add an actor",
            description = "Add an actor to the database.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PostMapping("")
    public ResponseEntity<ResponseDto<ActorDto>> addAnActor(
            @Valid @RequestBody ActorAddDto actorAddDto) {
        ResponseDto<ActorDto> response = actorService.addAnActor(actorAddDto);
        simpMessageTemplate.convertAndSend("/topic/actors", response.getData());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(tags = "Actor Service", summary = "Update an actor",
            description = "Update an actor in the database.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<ActorDto>> updateAnActor(@PathVariable Integer id, @Valid @RequestBody ActorUpdateDto actorUpdateDto) {
        ResponseDto<ActorDto> response = actorService.updateAnActor(id, actorUpdateDto);
        if (response.getData() == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Actor Service", summary = "Delete an actor",
            description = "Delete an actor in the database.",
            responses = {@ApiResponse(responseCode = "200", description = "OK", useReturnTypeSchema = true)})
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteAnActor(@PathVariable Integer id) {
        ResponseDto<?> response = actorService.deleteAnActor(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}