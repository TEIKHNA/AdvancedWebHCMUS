package com.hcmus.sakila.dto.request;

import com.hcmus.sakila.domain.Language;
import com.hcmus.sakila.domain.type.MpaaRating;
import com.hcmus.sakila.validator.EnumValid;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilmCreateDto {
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
    private Integer rentalDuration;

    @NotNull
    private BigDecimal rentalRate;

    @NotNull
    private Short length;

    @NotNull
    private BigDecimal replacementCost;

    @NotNull(message = "Rating is required")
    @EnumValid(enumClass = MpaaRating.class, message = "Invalid rating value")
    private MpaaRating rating;

    private String[] specialFeatures;




}