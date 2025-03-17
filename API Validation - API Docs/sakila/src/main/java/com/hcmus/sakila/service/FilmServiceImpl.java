package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.dto.response.FilmDto;
import com.hcmus.sakila.dto.response.FilmStatisticsDto;
import com.hcmus.sakila.dto.response.ResponseDto;
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

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByRating(Rating rating, Integer number, Integer page) {
        page = page <= 0 ? 0 : page - 1;
        Pageable filmRange = PageRequest.of(page, number);
        List<Film> films = filmRepository.findByRating(rating.getValue(), filmRange);
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).toList();
        if (filmDtos.isEmpty()) {
            return new ResponseDto<>(filmDtos, "There are no films with this rating");
        }
        return new ResponseDto<>(filmDtos, "Success get list of films by given rating!");
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByReleaseYear(Integer releaseYear, Integer number, Integer page) {
        page = page <= 0 ? 0 : page - 1;
        Pageable filmRange = PageRequest.of(page, number);
        List<Film> films = filmRepository.findAllByReleaseYear(releaseYear, filmRange);
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).toList();
        if (filmDtos.isEmpty()) {
            return new ResponseDto<>(filmDtos, "There are no films with this release year");
        }
        return new ResponseDto<>(filmDtos, "Success get list of films by given release year!");
    }

    @Override
    public ResponseDto<List<FilmDto>> getFilmsByLanguage(Integer languageId, Integer number, Integer page) {
        Optional<Language> language = languageRepository.findById(languageId);
        page = page <= 0 ? 0 : page - 1;
        Pageable filmRange = PageRequest.of(page, number);
        List<Film> films = filmRepository.findAllByLanguage(language, filmRange);
        List<FilmDto> filmDtos = films.stream().map(FilmDto::new).toList();
        if (filmDtos.isEmpty()) {
            return new ResponseDto<>(filmDtos, "There are no films with this language");
        }
        return new ResponseDto<>(filmDtos, "Success get list of films by given language!");
    }

    @Override
    public void updateFilm(Integer filmId, FilmUpdateDto film) {
        Language originalLanguage = null;
        Language language = null;

        if (film.getLanguageId() != null) {
            language = languageRepository.findById(film.getLanguageId())
                    .orElseThrow(() -> new RuntimeException("Language not found"));
        }
        if (film.getOriginalLanguageId() != null) {
            originalLanguage = languageRepository.findById(film.getOriginalLanguageId())
                    .orElseThrow(() -> new RuntimeException("Original language not found"));
        }
        Film oldFilm = filmRepository.findById(filmId)
                .orElseThrow(() -> new RuntimeException("Film not found"));
        oldFilm.setTitle(film.getTitle() != null ? film.getTitle() : oldFilm.getTitle());
        oldFilm.setDescription(film.getDescription() != null ? film.getDescription() : oldFilm.getDescription());
        oldFilm.setReleaseYear(film.getReleaseYear() != null ? film.getReleaseYear() : oldFilm.getReleaseYear());
        oldFilm.setLanguage(language != null ? language : oldFilm.getLanguage());
        oldFilm.setOriginalLanguage(originalLanguage != null ? originalLanguage : oldFilm.getOriginalLanguage());
        oldFilm.setRentalDuration(film.getRentalDuration() != null ? film.getRentalDuration() : oldFilm.getRentalDuration());
        oldFilm.setRentalRate(film.getRentalRate() != null ? film.getRentalRate() : oldFilm.getRentalRate());
        oldFilm.setLength(film.getLength() != null ? film.getLength() : oldFilm.getLength());
        oldFilm.setReplacementCost(film.getReplacementCost() != null ? film.getReplacementCost() : oldFilm.getReplacementCost());
        oldFilm.setRating(film.getRating() != null ? film.getRating() : oldFilm.getRating());
        oldFilm.setSpecialFeatures(film.getSpecialFeatures() != null ? film.getSpecialFeatures() : oldFilm.getSpecialFeatures());
        oldFilm.setLastUpdate(Instant.now());
        filmRepository.save(oldFilm);
    }

    public void deleteFilm(Integer filmId) {
        try {
            filmRepository.deleteById(filmId);
        } catch (Exception e) {
            throw new RuntimeException("Cannot delete film with id " + filmId);
        }
    }
    
    @Override
    public ResponseDto<FilmDto> createFilm(FilmCreateDto film) {
        Language originalLanguage = null;
        Language language = null;

        if (film.getLanguageId() != null) {
            language = languageRepository.findById(film.getLanguageId())
                    .orElseThrow(() -> new RuntimeException("Language not found"));
        }
        if (film.getOriginalLanguageId() != null) {
            originalLanguage = languageRepository.findById(film.getOriginalLanguageId())
                    .orElseThrow(() -> new RuntimeException("Original language not found"));
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
        return new ResponseDto<>(new FilmDto(savedFilm), "Success create film!");
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
