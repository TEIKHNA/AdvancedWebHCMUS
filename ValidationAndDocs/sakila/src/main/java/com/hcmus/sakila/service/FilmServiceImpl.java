package com.hcmus.sakila.service;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.type.MpaaRating;
import com.hcmus.sakila.dto.request.FilmCreateDto;
import com.hcmus.sakila.dto.request.FilmUpdateDto;
import com.hcmus.sakila.repository.FilmRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FilmServiceImpl implements FilmService {

    private final FilmRepository filmRepository;

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
        oldFilm.setRating(film.getRating());
        oldFilm.setSpecialFeatures(film.getSpecialFeatures());
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
                .rating(film.getRating())
                .specialFeatures(film.getSpecialFeatures())
                .lastUpdate(LocalDateTime.now())
                .build();
        filmRepository.save(newFilm);
    }
}
