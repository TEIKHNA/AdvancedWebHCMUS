package com.hcmus.sakila.dto.request;

import com.hcmus.sakila.model.type.Rating;
import com.hcmus.sakila.model.type.SpecialFeature;
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
public class FilmCreateDto {
    @Size(min = 1, max = 255, message = "The title must be between 1 and 255 characters")
    @NotNull(message = "Title is required")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Title must contain only letters and spaces")
    @Schema(description = "Title of the film", example = "The Avengers")
    private String title;

    @NotNull(message = "Description is required")
    @Schema(description = "Description of the film", example = "The Avengers is a 2012 American superhero film")
    private String description;

    @NotNull(message = "Release year is required")
    @Positive(message = "Release year must be a positive number")
    @Schema(description = "Release year of the film", example = "2012")
    private Integer releaseYear;

    @NotNull(message = "Language id is required")
    @Positive(message = "Language id must be a positive number")
    @Schema(description = "Language id of the film", example = "1")
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

    @NotNull(message = "Length is required")
    @Positive(message = "Length must be a positive number")
    @Schema(description = "Length of the film", example = "120")
    private Short length;

    @Schema(description = "Replacement cost of the film", example = "19.99")
    @Digits(integer = 3, fraction = 2, message = "Replacement cost must have up to 3 digits and 2 fractions")
    private BigDecimal replacementCost;

    @Schema(description = "Rating of the film", example = "PG-13")
    private Rating rating;

    @NotNull(message = "Special features is required")
    @Schema(description = "Special features of the film", example = "[\"Trailers\", \"Commentaries\", \"Deleted Scenes\", \"Behind the Scenes\"]")
    private List<SpecialFeature> specialFeatures;
}