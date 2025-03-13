package com.hcmus.sakila.service;

<<<<<<< HEAD
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
=======
import com.hcmus.sakila.dto.request.ActorAddDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
>>>>>>> feat-CRUD-film
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface FilmService {
    ResponseDto<List<FilmDto>> getFilmsByRating(RatingType rating,
                                                Integer number,
                                                Integer page);

<<<<<<< HEAD
    ResponseDto<List<FilmDto>> getFilmsByReleaseYear(Integer releaseYear,
                                                     Integer number,
                                                     Integer page);

    ResponseDto<List<FilmDto>> getFilmsByLanguage(Integer language_id,
                                                  Integer number,
                                                  Integer page);
=======

    void updateFilm(Integer film_id, FilmUpdateDto film);

    void deleteFilm(Integer film_id);

    void createFilm(FilmCreateDto film);
>>>>>>> feat-CRUD-film
}
