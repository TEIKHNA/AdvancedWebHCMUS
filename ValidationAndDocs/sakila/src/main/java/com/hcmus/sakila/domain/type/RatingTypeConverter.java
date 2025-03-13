package com.hcmus.sakila.domain.type;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingTypeConverter implements AttributeConverter<RatingType, String> {
    @Override
    public String convertToDatabaseColumn(RatingType attribute) {
        if (attribute == null) return null;
        return attribute.name().replace("_", "-");
    }

    @Override
    public RatingType convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return RatingType.valueOf(dbData.replace("-", "_"));
    }
}
