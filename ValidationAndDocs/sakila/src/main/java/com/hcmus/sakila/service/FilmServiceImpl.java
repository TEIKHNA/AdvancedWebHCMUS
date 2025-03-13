package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    @Override
    public ResponseDto<Integer> countFilms() {
        Integer totalFilms = (int) filmRepository.count();
        return new ResponseDto<>(totalFilms, "Success get total films!");
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsList() {
        List<Film> films = filmRepository.findAll();
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(filmDtos, "Success get films list!");
    }

    @Override
    public ResponseDto<List<FilmDto>> searchFilmsByTitle(String keyword) {
        List<Film> films = filmRepository.findByTitleContainingIgnoreCase(keyword);
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(filmDtos, "Success get films list by keyword " + keyword + "!");
    }

    @Override
    public ResponseDto<FilmDto> getFilmById(Integer filmId) {
        Film film = filmRepository.findById(filmId).orElse(null);
        if (film == null) {
            return new ResponseDto<>(null, "Film not found!");
        }
        return new ResponseDto<>(new FilmDto(film), "Success get film by id " + filmId + "!");
    }
}
