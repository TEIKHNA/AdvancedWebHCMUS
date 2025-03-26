package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.type.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FilmStatisticsDto {

    private Rating rating;

    private Long count;
}
