package com.hcmus.sakila.domain.converter;

import com.hcmus.sakila.domain.type.SpecialFeature;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.List;
import java.util.stream.Collectors;

@Converter(autoApply = true)
public class SpecialFeatureConverter implements AttributeConverter<List<SpecialFeature>, String> {

    @Override
    public String convertToDatabaseColumn(List<SpecialFeature> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        return attribute.stream().map(SpecialFeature::getValue).collect(Collectors.joining(","));
    }

    @Override
    public List<SpecialFeature> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) return Collections.emptyList();
        return Arrays.stream(dbData.replaceAll("[{}\"]", "").split(","))
                .map(SpecialFeature::fromValue)
                .collect(Collectors.toList());
    }
}
