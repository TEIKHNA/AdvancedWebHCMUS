package com.hcmus.sakila.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EnumValidValidator.class) // Liên kết với validator
@Target({ElementType.FIELD, ElementType.PARAMETER}) // Áp dụng cho field hoặc parameter
@Retention(RetentionPolicy.RUNTIME) // Giữ annotation khi chạy
public @interface EnumValid {
    String message() default "Invalid enum value"; // Thông báo lỗi mặc định
    Class<?>[] groups() default {}; // Hỗ trợ validation groups
    Class<? extends Payload>[] payload() default {}; // Hỗ trợ payload
    Class<? extends Enum<?>> enumClass(); // Lớp enum cần kiểm tra
}