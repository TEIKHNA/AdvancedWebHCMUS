package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.model.Film;
import com.hcmus.sakila.model.type.Rating;
import com.hcmus.sakila.model.type.SpecialFeature;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @Schema(description = "Id of a film", example = "1")
    private Integer id;

    @Schema(description = "Title of a film", example = "An awesome film")
    private String title;

    @Schema(description = "Description of a film", example = "A film about ...")
    private String description;

    @Schema(description = "Release year of a film", example = "2021")
    private Integer releaseYear;

    @Schema(description = "Language of a film")
    private LanguageDto language;

    @Schema(description = "Original language of a film")
    private LanguageDto originalLanguage;

    @Schema(description = "Rental duration of a film", example = "3")
    private Short rentalDuration;

    @Schema(description = "Rental rate of a film", example = "4.99")
    private BigDecimal rentalRate;

    @Schema(description = "Length of a film", example = "120")
    private Short length;

    @Schema(description = "Replacement cost of a film", example = "19.99")
    private BigDecimal replacementCost;

    @Schema(description = "Last update date", example = "2018-12-30T19:34:50.63Z")
    private Instant lastUpdate;

    @Schema(description = "Special features of a film", example = "[ \"Trailers\", \"Commentaries\" ]")
    private List<SpecialFeature> specialFeatures;

    @Schema(description = "Rating of a film", example = "PG")
    private Rating rating;

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