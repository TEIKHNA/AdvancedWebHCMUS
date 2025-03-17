package com.hcmus.sakila.controller;

import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
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

    @Operation(tags = "Film Statistic Service", summary = "Count total films",
            description = "Count the total number of films.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @GetMapping("/count")
    public ResponseEntity<ResponseDto<Integer>> countTotalFilms() {
        ResponseDto<Integer> response = filmService.countFilms();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Service", summary = "Retrieve all films",
            description = "Retrieve all films.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @GetMapping("/all")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsList() {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Service", summary = "Search films by title",
            description = "Search films by title using a given keyword.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<FilmDto>>> searchFilm(@RequestParam String q) {
        ResponseDto<List<FilmDto>> response = filmService.searchFilmsByTitle(q);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Simple CRUD Service", summary = "Retrieve film information",
            description = "Retrieve film information by a given id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @GetMapping("get/{id}")
    public ResponseEntity<ResponseDto<FilmDto>> getFilmInfo(@PathVariable(value = "id") Integer id) {
        ResponseDto<FilmDto> response = filmService.getFilmById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Simple CRUD Service", summary = "Update film information",
            description = "Update film information by a given id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDto<?>> updateFilm(
            @PathVariable Integer id,
            @Valid @RequestBody FilmUpdateDto film) {
        filmService.updateFilm(id, film);
        ResponseDto<?> response = new ResponseDto<>(null, "Film updated!");
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Simple CRUD Service", summary = "Delete film",
            description = "Delete film by a given id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true)})
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<?>> deleteFilm(@PathVariable Integer id) {
        filmService.deleteFilm(id);
        ResponseDto<?> response = new ResponseDto<>(null, "Film deleted!");
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Simple CRUD Service", summary = "Create film",
            description = "Create a new film.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", useReturnTypeSchema = true)})
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<FilmDto>> createFilm(@Valid @RequestBody FilmCreateDto film) {
        ResponseDto<FilmDto> response = filmService.createFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(tags = "Film Service", summary = "Retrieve films by rating",
            description = "Retrieve films by a given rating.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/rating/{rating}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByRating(
            @Parameter(name = "Rating", description = "[G, PG, PG-13, R, NC-17]", example = "PG-13")
            @PathVariable String rating,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByRating(Rating.fromValue(rating), size, page);
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
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByReleasingYear(
            @PathVariable(value = "releasing_year") Integer releasingYear,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByReleaseYear(releasingYear, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid year: " + releasingYear));
        }
    }

    @Operation(tags = "Film Service", summary = "Retrieve films by language",
            description = "Retrieve films by a given language.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/language/{language_id}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByLanguage(
            @PathVariable(value = "language_id") Integer languageId,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        try {
            ResponseDto<List<FilmDto>> response = filmService.getFilmsByLanguage(languageId, size, page);
            if (response.getData() == null) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto<>(null, "Invalid language id: " + languageId));
        }
    }

    @Operation(tags = "Film Statistic Service", summary = "Retrieve film statistics by rating",
            description = "Retrieve the number of films by each rating.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/statistics")
    public ResponseEntity<ResponseDto<List<FilmStatisticsDto>>> getFilmStatisticsByRating() {
        ResponseDto<List<FilmStatisticsDto>> response = filmService.getFilmStatisticsByRating();
        if (response.getData() == null || response.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Statistic Service", summary = "Retrieve longest films",
            description = "Retrieve the longest films with a specified limit.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/longest")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getLongestFilms(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseDto<List<FilmDto>> response = filmService.getLongestFilms(limit);
        if (response.getData() == null || response.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Statistic Service", summary = "Retrieve most expensive films",
            description = "Retrieve the most expensive films with a specified limit.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ok", useReturnTypeSchema = true),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content(schema = @Schema())),})
    @GetMapping("/most-expensive")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getMostExpensiveFilms(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseDto<List<FilmDto>> response = filmService.getMostExpensiveFilms(limit);
        if (response.getData() == null || response.getData().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}