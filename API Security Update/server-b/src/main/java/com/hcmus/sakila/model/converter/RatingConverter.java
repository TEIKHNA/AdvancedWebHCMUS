package com.hcmus.sakila.model.converter;

import com.hcmus.sakila.model.type.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter <Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating rate) {
        return rate.getValue();
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        return Rating.fromValue(dbData);
    }
}
