package com.hcmus.sakila.domain.type;

public enum MpaaRating {
    G, PG, PG_13("PG-13"), R, NC_17("NC-17");

    private final String value;

    MpaaRating() {
        this.value = name();
    }

    MpaaRating(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}