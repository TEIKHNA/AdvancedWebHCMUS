package com.hcmus.sakila.domain.type;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RatingType {
    G("G"), PG("PG"), PG_13("PG-13"), R("R"), NC_17("NC-17");

    private final String value;

    RatingType(String value) {
        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}

