package com.hcmus.sakila.controller;

import com.hcmus.sakila.model.type.Rating;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.FilmService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/films")
public class FilmController {

    private final FilmService filmService;

    @Operation(tags = "[5th Week] Security API - Server B", summary = "Retrieve all films",
            description = "Retrieve all films.")
    @GetMapping
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsList() {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsList();
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film CRUD Service", summary = "Retrieve film information",
            description = "Retrieve film information by a given id.")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<FilmDto>> getFilmInfo(@PathVariable(value = "id") Integer id) {
        ResponseDto<FilmDto> response = filmService.getFilmById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film CRUD Service", summary = "Update film information",
            description = "Update film information by a given id.")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<FilmDto>> updateFilm(
            @PathVariable Integer id,
            @Valid @RequestBody FilmUpdateDto film) {
        ResponseDto<FilmDto> response = filmService.updateFilm(id, film);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film CRUD Service", summary = "Delete film",
            description = "Delete film by a given id.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteFilm(@PathVariable Integer id) {
        ResponseDto<?> response = filmService.deleteFilm(id);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film CRUD Service", summary = "Create film",
            description = "Create a new film.")
    @PostMapping
    public ResponseEntity<ResponseDto<FilmDto>> createFilm(@Valid @RequestBody FilmCreateDto film) {
        ResponseDto<FilmDto> response = filmService.createFilm(film);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Query Service", summary = "Search films by title",
            description = "Search films by title using a given keyword.")
    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<FilmDto>>> searchFilm(@RequestParam String q) {
        ResponseDto<List<FilmDto>> response = filmService.searchFilmsByTitle(q);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Query Service", summary = "Retrieve films by rating",
            description = "Retrieve films by a given rating.")
    @GetMapping("/rating/{rating}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByRating(
            @PathVariable(value = "rating") String rating,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsByRating(Rating.fromValue(rating), size, page);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Query Service", summary = "Retrieve films by releasing year",
            description = "Retrieve films by a given releasing year.")
    @GetMapping("/release-year/{release_year}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByReleasingYear(
            @PathVariable(value = "release_year") Integer releasingYear,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsByReleaseYear(releasingYear, size, page);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Query Service", summary = "Retrieve films by language",
            description = "Retrieve films by a given language.")
    @GetMapping("/language/{language_id}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByLanguage(
            @PathVariable(value = "language_id") Integer languageId,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsByLanguage(languageId, size, page);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Statistic Service", summary = "Count total films",
            description = "Count the total number of films.")
    @GetMapping("/total")
    public ResponseEntity<ResponseDto<Integer>> countTotalFilms() {
        ResponseDto<Integer> response = filmService.countFilms();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(tags = "Film Statistic Service", summary = "Retrieve film statistics by rating",
            description = "Retrieve the number of films by each rating.")
    @GetMapping("/statistics")
    public ResponseEntity<ResponseDto<List<FilmStatisticsDto>>> getFilmStatisticsByRating() {
        ResponseDto<List<FilmStatisticsDto>> response = filmService.getFilmStatisticsByRating();
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Statistic Service", summary = "Retrieve longest films",
            description = "Retrieve the longest films with a specified limit.")
    @GetMapping("/longest")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getLongestFilms(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseDto<List<FilmDto>> response = filmService.getLongestFilms(limit);
        return ResponseEntity.ok(response);
    }

    @Operation(tags = "Film Statistic Service", summary = "Retrieve most expensive films",
            description = "Retrieve the most expensive films with a specified limit.")
    @GetMapping("/most-expensive")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getMostExpensiveFilms(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseDto<List<FilmDto>> response = filmService.getMostExpensiveFilms(limit);
        return ResponseEntity.ok(response);
    }
}