package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

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
}
