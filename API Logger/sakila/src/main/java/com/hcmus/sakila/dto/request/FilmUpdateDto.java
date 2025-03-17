package com.hcmus.sakila.dto.request;

import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.domain.type.SpecialFeature;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Title must contain only letters and spaces")
    @Schema(description = "Title of the film", example = "The Avengers")
    private String title;

    @Schema(description = "Description of the film", example = "The Avengers is a 2012 American superhero film")
    private String description;

    @Schema(description = "Release year of the film", example = "2012")
    @Positive(message = "Release year must be a positive number")
    private Integer releaseYear;

    @Schema(description = "Language id of the film", example = "1")
    @Positive(message = "Language id must be a positive number")
    private Integer languageId;

    @Schema(description = "Original language id of the film", example = "1")
    @Positive(message = "Original Language id must be a positive number")
    private Integer originalLanguageId;

    @Schema(description = "Rental duration of the film", example = "3")
    @PositiveOrZero(message = "Rental duration must be not a negative number")
    private Short rentalDuration;

    @Schema(description = "Rental rate of the film", example = "4.99")
    @Digits(integer = 3, fraction = 2, message = "Rental rate must have up to 3 digits and 2 fractions")
    private BigDecimal rentalRate;

    @Schema(description = "Length of the film", example = "120")
    @Positive(message = "Length must be a positive number")
    private Short length;

    @Schema(description = "Replacement cost of the film", example = "19.99")
    @Digits(integer = 3, fraction = 2, message = "Replacement cost must have up to 3 digits and 2 fractions")
    private BigDecimal replacementCost;

    @Schema(description = "Rating of the film", example = "PG-13")
    private Rating rating;

    @Schema(description = "Special features of the film", example = "[\"Trailers\", \"Commentaries\", \"Deleted Scenes\", \"Behind the Scenes\"]")
    private List<SpecialFeature> specialFeatures;
}