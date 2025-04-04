package com.hcmus.sakila.service;

import com.hcmus.sakila.model.Film;
import com.hcmus.sakila.model.Language;
import com.hcmus.sakila.model.type.Rating;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;
import com.hcmus.sakila.dto.response.Status;
import com.hcmus.sakila.repository.FilmRepository;
import com.hcmus.sakila.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

    private final LanguageRepository languageRepository;

    @Override
    public ResponseDto<Integer> countFilms() {
        Integer filmsTotal = (int) filmRepository.count();
        return new ResponseDto<>(Status.SUCCESS, filmsTotal, null);
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsList() {
        List<Film> films = filmRepository.findAll();
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }

    @Override
    public ResponseDto<List<FilmDto>> searchFilmsByTitle(String keyword) {
        List<Film> films = filmRepository.findByTitleContainingIgnoreCase(keyword);
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }

    @Override
    public ResponseDto<FilmDto> getFilmById(Integer filmId) {
        Film film = filmRepository.findById(filmId).orElse(null);
        if (film == null) {
            return new ResponseDto<>(Status.FAIL, null, null);
        }
        return new ResponseDto<>(Status.SUCCESS, new FilmDto(film), null);
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByRating(Rating rating, Integer number, Integer page) {
        page = page <= 0 ? 0 : page - 1;
        Pageable filmRange = PageRequest.of(page, number);
        List<Film> films = filmRepository.findByRating(rating.getValue(), filmRange);
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByReleaseYear(Integer releaseYear, Integer number, Integer page) {
        page = page <= 0 ? 0 : page - 1;
        Pageable filmRange = PageRequest.of(page, number);
        List<Film> films = filmRepository.findAllByReleaseYear(releaseYear, filmRange);
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByLanguage(Integer languageId, Integer number, Integer page) {
        Optional<Language> language = languageRepository.findById(languageId);
        page = page <= 0 ? 0 : page - 1;
        Pageable filmRange = PageRequest.of(page, number);
        List<Film> films = filmRepository.findAllByLanguage(language, filmRange);
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).toList();
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }

    @Override
    public ResponseDto<FilmDto> updateFilm(Integer filmId, FilmUpdateDto filmUpdate) {
        Language originalLanguage = null;
        Language language = null;

        if (filmUpdate.getOriginalLanguageId() != null) {
            originalLanguage = languageRepository.findById(filmUpdate.getOriginalLanguageId()).orElse(null);
            if (originalLanguage == null) {
                return new ResponseDto<>(Status.FAIL, null,
                        List.of("Original language not found with id " + filmUpdate.getOriginalLanguageId()));
            }
        }
        if (filmUpdate.getLanguageId() != null) {
            language = languageRepository.findById(filmUpdate.getLanguageId()).orElse(null);
            if (language == null) {
                return new ResponseDto<>(Status.FAIL, null,
                        List.of("Language not found with id " + filmUpdate.getLanguageId()));
            }
        }

        Film film = filmRepository.findById(filmId).orElse(null);
        if (film == null) {
            return new ResponseDto<>(Status.FAIL, null, List.of("Film not found with id" + filmId));
        }

        film.setTitle(filmUpdate.getTitle() != null ? filmUpdate.getTitle() : film.getTitle());
        film.setDescription(filmUpdate.getDescription() != null ? filmUpdate.getDescription() : film.getDescription());
        film.setReleaseYear(filmUpdate.getReleaseYear() != null ? filmUpdate.getReleaseYear() : film.getReleaseYear());
        film.setLanguage(language != null ? language : film.getLanguage());
        film.setOriginalLanguage(originalLanguage != null ? originalLanguage : film.getOriginalLanguage());
        film.setRentalDuration(filmUpdate.getRentalDuration() != null ? filmUpdate.getRentalDuration() : film.getRentalDuration());
        film.setRentalRate(filmUpdate.getRentalRate() != null ? filmUpdate.getRentalRate() : film.getRentalRate());
        film.setLength(filmUpdate.getLength() != null ? filmUpdate.getLength() : film.getLength());
        film.setReplacementCost(filmUpdate.getReplacementCost() != null ? filmUpdate.getReplacementCost() : film.getReplacementCost());
        film.setRating(filmUpdate.getRating() != null ? filmUpdate.getRating() : film.getRating());
        film.setSpecialFeatures(filmUpdate.getSpecialFeatures() != null ? filmUpdate.getSpecialFeatures() : film.getSpecialFeatures());
        film.setLastUpdate(Instant.now());
        Film savedFilm = filmRepository.save(film);
        return new ResponseDto<>(Status.SUCCESS, new FilmDto(savedFilm), null);
    }

    @Override
    public ResponseDto<?> deleteFilm(Integer filmId) {
        try {
            filmRepository.deleteById(filmId);
            return new ResponseDto<>(Status.SUCCESS, null, null);
        } catch (Exception e) {
            return new ResponseDto<>(Status.FAIL, null, List.of(e.getMessage()));
        }
    }

    @Override
    public ResponseDto<FilmDto> createFilm(FilmCreateDto film) {
        Language originalLanguage = null;
        Language language = null;
        if (film.getOriginalLanguageId() != null) {
            originalLanguage = languageRepository.findById(film.getOriginalLanguageId()).orElse(null);
            if (originalLanguage == null) {
                return new ResponseDto<>(Status.FAIL, null,
                        List.of("Original language not found with id" + film.getOriginalLanguageId()));
            }
        }
        if (film.getLanguageId() != null) {
            language = languageRepository.findById(film.getLanguageId()).orElse(null);
            if (language == null) {
                return new ResponseDto<>(Status.FAIL, null, List.of("Language not found with id" + film.getLanguageId()));
            }
        }
        Film newFilm = Film.builder()
                .title(film.getTitle())
                .description(film.getDescription())
                .releaseYear(film.getReleaseYear())
                .language(language)
                .originalLanguage(originalLanguage)
                .rentalDuration(film.getRentalDuration())
                .rentalRate(film.getRentalRate())
                .length(film.getLength())
                .replacementCost(film.getReplacementCost())
                .rating(film.getRating())
                .specialFeatures(film.getSpecialFeatures())
                .lastUpdate(Instant.now())
                .build();
        Film savedFilm = filmRepository.save(newFilm);
        return new ResponseDto<>(Status.SUCCESS, new FilmDto(savedFilm), null);
    }

    @Override
    public ResponseDto<List<FilmStatisticsDto>> getFilmStatisticsByRating() {
        List<Object[]> results = filmRepository.countFilmsByRating();
        List<FilmStatisticsDto> statistics = results.stream()
                .map(result -> new FilmStatisticsDto(Rating.valueOf(result[0].toString()), (Long) result[1]))
                .collect(Collectors.toList());
        return new ResponseDto<>(Status.SUCCESS, statistics, null);
    }

    @Override
    public ResponseDto<List<FilmDto>> getLongestFilms(Integer limit) {
        List<Film> films = filmRepository.findLongestFilms(PageRequest.of(0, limit));
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).collect(Collectors.toList());
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }

    @Override
    public ResponseDto<List<FilmDto>> getMostExpensiveFilms(Integer limit) {
        List<Film> films = filmRepository.findMostExpensiveFilms(PageRequest.of(0, limit));
        List<FilmDto> filmsList = films.stream().map(FilmDto::new).collect(Collectors.toList());
        return new ResponseDto<>(Status.SUCCESS, filmsList, null);
    }
}
