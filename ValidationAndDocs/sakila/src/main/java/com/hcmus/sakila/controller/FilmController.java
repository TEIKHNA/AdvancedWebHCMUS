package com.hcmus.sakila.controller;

import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/film")
public class FilmController {

    private final FilmService filmService;


    @PutMapping("update/{film_id}")
    ResponseDto<Object> updateFilm(@PathVariable Integer film_id, @RequestBody FilmUpdateDto film) {
        filmService.updateFilm(film_id, film);
        return ResponseDto.builder()
                .message("Film updated")
                .build();
    }

    @DeleteMapping("/delete/{film_id}")
    ResponseDto<Object> deleteFilm(@PathVariable Integer film_id) {
        filmService.deleteFilm(film_id);
        return ResponseDto.builder()
                .message("Film deleted ")
                .build();
    }

    @PostMapping("/create")
    ResponseDto<Object> createFilm(@RequestBody FilmCreateDto film) {
        filmService.createFilm(film);
        return ResponseDto.builder()
                .message("Film created")
                .build();
    }


    @Operation(tags = "Film Service", summary = "Retrieve films by rating",
            description = "Retrieve films by a given rating.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/rating/{rating}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByRating(@PathVariable String rating,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                       @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            RatingType ratingEnum = RatingType.valueOf(rating.toUpperCase().replace("-", "_"));
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByRating(ratingEnum, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid rating: " + rating));
        }
    }

    @Operation(tags = "Film Service", summary = "Retrieve films by releasing year",
            description = "Retrieve films by a given releasing year.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/year/{releasing_year}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByReleasingYear(@PathVariable Integer releasing_year,
                                                                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                       @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByReleaseYear(releasing_year, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid year: " + releasing_year));
        }
    }

    @Operation(tags = "Film Service", summary = "Retrieve films by language",
            description = "Retrieve films by a given language.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/language/{language_id}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByLanguage(@PathVariable Integer language_id,
                                                                              @RequestParam(value = "size", defaultValue = "10") Integer size,
                                                                              @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByLanguage(language_id, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid language id: " + language_id));
        }
    }


}