package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.Film;
import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.RatingType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmDto {

    @Schema(description = "Id of a film", example = "1")
    private Integer id;

    @Schema(description = "Title of a film", example = "Gone With The Wind")
    private String title;

    @Schema(description = "Description of a film", example = "This movie is about...")
    private String description;

    @Schema(description = "Release year of a film", example = "1939")
    private Integer releaseYear;

    @Schema(description = "Subtitle language of a film", example = "Vietnamese")
    private String language;

    @Schema(description = "Original language of a film", example = "English")
    private Language originalLanguage;

    @Schema(description = "Rental duration of a film", example = "2")
    private Short rentalDuration;

    @Schema(description = "Rental rate of a film", example = "0.99")
    private BigDecimal rentalRate;

    @Schema(description = "Length of a film", example = "86")
    private Short length;

    @Schema(description = "Replacement cost of a film", example = "20.99")
    private BigDecimal replacementCost;

    @Schema(description = "Rating of a film", example = "G")
    private RatingType rating;

    @Schema(description = "Special features of a film", example = "{Behind The Scenes}")
    private List<String> specialFeatures;

    @Schema(description = "Last update of a film", example = "2006-02-15 05:03:42.000000")
    private LocalDateTime lastUpdate;

    public FilmDto(Film film) {
        this.id = film.getId();
        this.title = film.getTitle();
        this.description = film.getDescription();
        this.releaseYear = film.getReleaseYear();
        this.language = film.getLanguage().getName().replace(" ", "");
        this.originalLanguage = film.getOriginalLanguage();
        this.rentalDuration = film.getRentalDuration();
        this.rentalRate = film.getRentalRate();
        this.length = film.getLength();
        this.replacementCost = film.getReplacementCost();
        this.rating = film.getRating();
        this.specialFeatures = film.getSpecialFeatures();
        this.lastUpdate = film.getLastUpdate();
    }
}
