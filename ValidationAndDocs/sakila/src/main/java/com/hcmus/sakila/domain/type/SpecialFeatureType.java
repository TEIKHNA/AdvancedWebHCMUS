package com.hcmus.sakila.domain.type;

public enum SpecialFeatureType {
    TRAILERS("Trailers"),
    COMMENTARIES("Commentaries"),
    DELETED_SCENES("Deleted Scenes"),
    BEHIND_THE_SCENES("Behind the Scenes");

    private final String label;

    SpecialFeatureType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static SpecialFeatureType fromString(String text) {
        for (SpecialFeatureType feature : SpecialFeatureType.values()) {
            if (feature.label.equalsIgnoreCase(text)) {
                return feature;
            }
        }
        throw new IllegalArgumentException("Unknown special feature: " + text);
    }
}
