package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.request.ActorAddDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;
import java.util.Map;

public interface FilmService {
    ResponseDto<List<FilmDto>> getFilmsByRating(RatingType rating,
                                                Integer number,
                                                Integer page);

    ResponseDto<List<FilmDto>> getFilmsByReleaseYear(Integer releaseYear,
                                                     Integer number,
                                                     Integer page);

    ResponseDto<List<FilmDto>> getFilmsByLanguage(Integer language_id,
                                                  Integer number,
                                                  Integer page);

    void updateFilm(Integer film_id, FilmUpdateDto film);

    void deleteFilm(Integer film_id);

    void createFilm(FilmCreateDto film);

    ResponseDto<List<FilmStatisticsDto>> getFilmStatisticsByRating();
    ResponseDto<List<FilmDto>> getLongestFilms(Integer limit);
    ResponseDto<List<FilmDto>> getMostExpensiveFilms(Integer limit);
    ResponseDto<Integer> countFilms();

    ResponseDto<List<FilmDto>> getFilmsList();

    ResponseDto<List<FilmDto>> searchFilmsByTitle(String keyword);

    ResponseDto<FilmDto> getFilmById(Integer filmId);
}
