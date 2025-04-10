package com.hcmus.sakila.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidValidator implements ConstraintValidator<EnumValid, String> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        this.enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Enum<?>[] enumValues = enumClass.getEnumConstants();
        for (Enum<?> enumValue : enumValues) {
            if (enumValue.toString().equals(value)) {
                return true;
            }
        }
        return false;
    }
}