package com.hcmus.sakila.dto.response;

import com.hcmus.sakila.domain.Language;
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

    private Integer id;

    private String name;

    private Instant lastUpdate;

    public LanguageDto(Language language) {
        this.id = language.getId();
        this.name = language.getName();
        this.lastUpdate = language.getLastUpdate();
    }
}