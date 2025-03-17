package com.hcmus.sakila.domain.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    SpecialFeature(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static SpecialFeature fromValue(String value) {
        for (SpecialFeature f : SpecialFeature.values()) {
            if (f.getValue().equalsIgnoreCase(value) || value.contains(f.getValue())) {
                return f;
            }
        }
        throw new IllegalArgumentException("Unknown special feature: " + value);
    }
}
