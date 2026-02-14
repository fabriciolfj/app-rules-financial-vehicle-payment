package com.github.fabriciolfj.validations;

import com.github.fabriciolfj.annotation.ValidDocument;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class DocumentValidator implements ConstraintValidator<ValidDocument, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }

        String cleanvalue = value.replaceAll("[^0-9]", "");

        if (cleanvalue.length() != 11) {
            return false;
        }

        if (cleanvalue.matches("(\\d)\\1{10}")) {
            return false;
        }

        try {
            int sum = 0;

            for (int i = 0; i < 9; i++) {
                sum += Character.getNumericValue(cleanvalue.charAt(i)) * (10 - i);
            }

            int firstDigit = 11 - (sum % 11);
            if (firstDigit >= 10) {
                firstDigit = 0;
            }

            if (firstDigit != Character.getNumericValue(cleanvalue.charAt(9))) {
                return false;
            }

            sum = 0;
            for (int i = 0; i < 10; i++) {
                sum += Character.getNumericValue(cleanvalue.charAt(i)) * (11 - i);
            }
            int secondDigit = 11 - (sum % 11);
            if (secondDigit >= 10) {
                secondDigit = 0;
            }

            return secondDigit == Character.getNumericValue(cleanvalue.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }
}
