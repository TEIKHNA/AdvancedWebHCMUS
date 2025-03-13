package com.hcmus.sakila.dto.request;

import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.RatingType;
import com.hcmus.sakila.validator.EnumValid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmUpdateDto {
    @Size(max = 255  ,message =  "The title must be not e")
    @NotNull
    private String title;

    private String description;

    private Integer releaseYear;

    @NotNull
    private Language language;

    @NotNull
    private Language originalLanguage;

    @NotNull
    private Short rentalDuration;

    @NotNull
    private BigDecimal rentalRate;

    @NotNull
    private Short length;

    @NotNull
    private BigDecimal replacementCost;


    @NotNull(message = "Rating is required")
    @EnumValid(enumClass = RatingType.class, message = "Invalid rating value")
    private RatingType rating;

    private String[] specialFeatures;

}