package com.github.fabriciolfj.annotation;

import com.github.fabriciolfj.validations.DocumentValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DocumentValidator.class)
public @interface ValidDocument {

    String message() default "document invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
