package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.repository.FilmRepository;
import com.hcmus.sakila.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByRating(RatingType rating,
                                                       Integer number,
                                                       Integer page) {
        page = page <= 0 ? 0 : page - 1;

        Pageable filmRange = PageRequest.of(page, number);

        String ratingStr = rating.name().replace("_", "-"); // PG_13 -> PG-13
        List<Film> films = filmRepository.findByRating(ratingStr, filmRange);

        List<FilmDto> filmDtos = films.stream()
                .map(FilmDto::new)
                .toList();


        if (filmDtos.isEmpty()) {
            return new ResponseDto<>(filmDtos, "There are no films with this rating");
        }

        return new ResponseDto<>(filmDtos, "Success get list of films by given rating!");
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByReleaseYear(Integer releaseYear,
                                                            Integer number,
                                                            Integer page) {
        page = page <= 0 ? 0 : page - 1;

        Pageable filmRange = PageRequest.of(page, number);

        List<Film> films = filmRepository.findAllByReleaseYear(releaseYear, filmRange);

        List<FilmDto> filmDtos = films.stream()
                .map(FilmDto::new)
                .toList();

        if (filmDtos.isEmpty()) {
            return new ResponseDto<>(filmDtos, "There are no films with this release year");
        }

        return new ResponseDto<>(filmDtos, "Success get list of films by given release year!");
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByLanguage(Integer language_id,
                                                         Integer number,
                                                         Integer page) {

        Optional<Language> language = languageRepository.findById(language_id);

        page = page <= 0 ? 0 : page - 1;

        Pageable filmRange = PageRequest.of(page, number);

        List<Film> films = filmRepository.findAllByLanguage(language, filmRange);

        List<FilmDto> filmDtos = films.stream()
                .map(FilmDto::new)
                .toList();

        if (filmDtos.isEmpty()) {
            return new ResponseDto<>(filmDtos, "There are no films with this language");
        }

        return new ResponseDto<>(filmDtos, "Success get list of films by given language!");
    }
}
