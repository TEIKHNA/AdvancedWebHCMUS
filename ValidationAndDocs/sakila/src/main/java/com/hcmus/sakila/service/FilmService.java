package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
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
    ResponseDto<List<FilmStatisticsDto>> getFilmStatisticsByRating();
    ResponseDto<List<FilmDto>> getLongestFilms(Integer limit);      
    ResponseDto<List<FilmDto>> getMostExpensiveFilms(Integer limit);                                           
}
