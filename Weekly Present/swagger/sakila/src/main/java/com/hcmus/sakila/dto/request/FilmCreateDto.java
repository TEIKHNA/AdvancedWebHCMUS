package com.hcmus.sakila.dto.request;

import com.hcmus.sakila.domain.type.Rating;
import com.hcmus.sakila.domain.type.SpecialFeature;
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
    private String title;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Release year is required")
    @Positive(message = "Release year must be a positive number")
    private Integer releaseYear;

    @NotNull(message = "Language id is required")
    @Positive(message = "Language id must be a positive number")
    private Integer languageId;

    @Positive(message = "Original Language id must be a positive number")
    private Integer originalLanguageId;

    @PositiveOrZero(message = "Rental duration must be not a negative number")
    private Short rentalDuration;

    @Digits(integer = 3, fraction = 2, message = "Rental rate must have up to 3 digits and 2 fractions")
    private BigDecimal rentalRate;

    @NotNull(message = "Length is required")
    @Positive(message = "Length must be a positive number")
    private Short length;

    @Digits(integer = 3, fraction = 2, message = "Replacement cost must have up to 3 digits and 2 fractions")
    private BigDecimal replacementCost;

    private Rating rating;

    @NotNull(message = "Special features is required")
    private List<SpecialFeature> specialFeatures;
}