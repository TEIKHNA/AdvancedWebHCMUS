package com.hcmus.sakila.domain.type;

public enum Rating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");

    private final String value;

    Rating(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Rating fromValue(String value) {
        for (Rating r : Rating.values()) {
            if (r.getValue().equalsIgnoreCase(value)) {
                return r;
            }
        }
        throw new IllegalArgumentException("Invalid rating: " + value);
    }
}