package com.hcmus.sakila.controller;

import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.service.FilmService;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<FilmDto>> getFilmInfo(@PathVariable(value = "id") Integer id) {
        ResponseDto<FilmDto> response = filmService.getFilmById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<FilmDto>> updateFilm(
            @PathVariable Integer id,
            @Valid @RequestBody FilmUpdateDto film) {
        ResponseDto<FilmDto> response = filmService.updateFilm(id, film);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<?>> deleteFilm(@PathVariable Integer id) {
        ResponseDto<?> response = filmService.deleteFilm(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<ResponseDto<FilmDto>> createFilm(@Valid @RequestBody FilmCreateDto film) {
        ResponseDto<FilmDto> response = filmService.createFilm(film);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsList() {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDto<List<FilmDto>>> searchFilm(@RequestParam String q) {
        ResponseDto<List<FilmDto>> response = filmService.searchFilmsByTitle(q);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rating/{rating}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByRating(
            @PathVariable(value = "rating") String rating,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsByRating(Rating.fromValue(rating), size, page);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/release-year/{release_year}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByReleasingYear(
            @PathVariable(value = "release_year") Integer releasingYear,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsByReleaseYear(releasingYear, size, page);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/language/{language_id}")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getFilmsByLanguage(
            @PathVariable(value = "language_id") Integer languageId,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "page", defaultValue = "0") Integer page) {
        ResponseDto<List<FilmDto>> response = filmService.getFilmsByLanguage(languageId, size, page);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/total")
    public ResponseEntity<ResponseDto<Integer>> countTotalFilms() {
        ResponseDto<Integer> response = filmService.countFilms();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/statistics")
    public ResponseEntity<ResponseDto<List<FilmStatisticsDto>>> getFilmStatisticsByRating() {
        ResponseDto<List<FilmStatisticsDto>> response = filmService.getFilmStatisticsByRating();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/longest")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getLongestFilms(
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseDto<List<FilmDto>> response = filmService.getLongestFilms(limit);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/most-expensive")
    public ResponseEntity<ResponseDto<List<FilmDto>>> getMostExpensiveFilms(@RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        ResponseDto<List<FilmDto>> response = filmService.getMostExpensiveFilms(limit);
        return ResponseEntity.ok(response);
    }
}