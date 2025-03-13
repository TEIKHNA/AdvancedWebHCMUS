package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface FilmService {

    ResponseDto<Integer> countFilms();

    ResponseDto<List<FilmDto>> getFilmsList();

    ResponseDto<List<FilmDto>> searchFilmsByTitle(String keyword);

    ResponseDto<FilmDto> getFilmById(Integer filmId);
}
