package com.hcmus.sakila.domain.type;

public enum SpecialFeature {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String value;

    SpecialFeature(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SpecialFeature fromValue(String value) {
        for (SpecialFeature f : SpecialFeature.values()) {
            if (f.getValue().equalsIgnoreCase(value)) {
                return f;
            }
        }
        throw new IllegalArgumentException("Unknown SpecialFeature: " + value);
    }
}
