package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.repository.FilmRepository;
import com.hcmus.sakila.repository.FilmRepository;
import com.hcmus.sakila.repository.LanguageRepository;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
    public void updateFilm(Integer film_id, FilmUpdateDto film) {
        Film oldFilm = filmRepository.findById(film_id)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        oldFilm.setTitle(film.getTitle());
        oldFilm.setDescription(film.getDescription());
        oldFilm.setReleaseYear(film.getReleaseYear());
        oldFilm.setLanguage(film.getLanguage());
        oldFilm.setOriginalLanguage(film.getOriginalLanguage());
        oldFilm.setRentalDuration(film.getRentalDuration());
        oldFilm.setRentalRate(film.getRentalRate());
        oldFilm.setLength(film.getLength());
        oldFilm.setReplacementCost(film.getReplacementCost());
//        oldFilm.setRating(film.getRating());
//        oldFilm.setSpecialFeatures(film.getSpecialFeatures());
        filmRepository.save(oldFilm);


    }

    public void deleteFilm(Integer film_id) {
        if (!filmRepository.existsById(film_id)) {
            throw new RuntimeException("Film not found");
        }
        try {
            filmRepository.deleteById(film_id);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete film with id " + film_id );
        }
    }


    @Override
    public void createFilm(FilmCreateDto film) {
        Film newFilm = Film.builder()
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .language(film.getLanguage())
                .originalLanguage(film.getOriginalLanguage())
                .rentalDuration(film.getRentalDuration())
                .rentalRate(film.getRentalRate())
                .length(film.getLength())
                .replacementCost(film.getReplacementCost())
                //.rating(film.getRating())
                //.specialFeatures(film.getSpecialFeatures())
                .lastUpdate(LocalDateTime.now())
                .build();
        filmRepository.save(newFilm);
    }

    @Override
    public ResponseDto<List<FilmStatisticsDto>> getFilmStatisticsByRating() {
        List<Object[]> results = filmRepository.countFilmsByRating();
        List<FilmStatisticsDto> statistics = results.stream()
                .map(result -> new FilmStatisticsDto(result[0].toString(), (Long) result[1]))
                .collect(Collectors.toList());
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
