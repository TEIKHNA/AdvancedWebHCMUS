package com.hcmus.sakila.service;

import com.hcmus.sakila.dto.request.ActorAddDto;
import com.hcmus.sakila.dto.request.ActorUpdateDto;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.ActorDto;
import com.hcmus.sakila.dto.response.ResponseDto;

import java.util.List;

public interface FilmService {


    void updateFilm(Integer film_id, FilmUpdateDto film);

    void deleteFilm(Integer film_id);

    void createFilm(FilmCreateDto film);
}
