package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.repository.FilmRepository;
import com.hcmus.sakila.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

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

    @Override
    public ResponseDto<Map<String, Long>> getFilmStatisticsByRating() {
        List<Object[]> results = filmRepository.countFilmsByRating();
        Map<String, Long> statistics = new HashMap<>();
        for (Object[] result : results) {
            String rating = result[0].toString(); // Convert RatingType to String
            Long count = (Long) result[1];
            statistics.put(rating, count);
        }
        if (statistics.isEmpty()) {
            return new ResponseDto<>(statistics, "There are no films in the database");
        }
        return new ResponseDto<>(statistics, "Success get film statistics by rating!");
    }

    @Override
    public ResponseDto<List<FilmDto>> getLongestFilms(Integer limit) {
        List<Film> films = filmRepository.findLongestFilms(PageRequest.of(0, limit));
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).collect(Collectors.toList());
        return new ResponseDto<>(filmDtos, "Success get longest films! (at most " + limit + ")");
    }

    @Override
    public ResponseDto<List<FilmDto>> getMostExpensiveFilms(Integer limit) {
        List<Film> films = filmRepository.findMostExpensiveFilms(PageRequest.of(0, limit));
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).collect(Collectors.toList());
        return new ResponseDto<>(filmDtos, "Success get most expensive films! (at most " + limit + ")");
    }
}
