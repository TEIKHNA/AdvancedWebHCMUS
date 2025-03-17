package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.domain.type.SpecialFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmDto {
    Integer id;
    String title;
    String description;
    Integer releaseYear;
    LanguageDto language;
    LanguageDto originalLanguage;
    Short rentalDuration;
    BigDecimal rentalRate;
    Short length;
    BigDecimal replacementCost;
    Instant lastUpdate;
    List<SpecialFeature> specialFeatures;
    Rating rating;

    public FilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.releaseYear = film.getReleaseYear();
        if (film.getLanguage() == null) {
            this.language = null;
        } else {
            this.language = new LanguageDto(film.getLanguage());
        }
        if (film.getOriginalLanguage() == null) {
            this.originalLanguage = null;
        } else {
            this.originalLanguage = new LanguageDto(film.getOriginalLanguage());
        }
        this.rentalDuration = film.getRentalDuration();
        this.rentalRate = film.getRentalRate();
        this.length = film.getLength();
        this.replacementCost = film.getReplacementCost();
        this.lastUpdate = film.getLastUpdate();
        this.specialFeatures = film.getSpecialFeatures();
        this.rating = film.getRating();
    }
}