package com.hcmus.sakila.dto.request;

import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.domain.type.SpecialFeature;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmUpdateDto {
    @Size(min = 1, max = 255, message = "The title must be between 1 and 255 characters")
    @Schema(description = "Title of the film", example = "The Avengers")
    private String title;

    @Schema(description = "Description of the film", example = "The Avengers is a 2012 American superhero film")
    private String description;

    @Schema(description = "Release year of the film", example = "2012")
    private Integer releaseYear;

    @Schema(description = "Language id of the film", example = "1")
    private Integer languageId;

    @Schema(description = "Original language id of the film", example = "1")
    private Integer originalLanguageId;

    @Schema(description = "Rental duration of the film", example = "3")
    private Short rentalDuration;

    @Schema(description = "Rental rate of the film", example = "4.99")
    private BigDecimal rentalRate;

    @Schema(description = "Length of the film", example = "120")
    private Short length;

    @Schema(description = "Replacement cost of the film", example = "19.99")
    private BigDecimal replacementCost;

    //    @EnumValid(enumClass = Rating.class, message = "Invalid rating value")
    @Schema(description = "Rating of the film", example = "PG-13")
    private Rating rating;

    @Schema(description = "Special features of the film", example = "[\"Trailers\", \"Commentaries\", \"Deleted Scenes\", \"Behind the Scenes\"]")
    private List<SpecialFeature> specialFeatures;
}