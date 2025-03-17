package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.Language;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LanguageDto {

    @Schema(description = "Id of a language", example = "1")
    private Integer id;

    @Schema(description = "Name of a language", example = "English")
    private String name;

    @Schema(description = "Last update date", example = "2018-12-30T19:34:50.63Z")
    private Instant lastUpdate;

    public LanguageDto(Language language) {
        this.id = language.getId();
        this.name = language.getName();
        this.lastUpdate = language.getLastUpdate();
    }
}