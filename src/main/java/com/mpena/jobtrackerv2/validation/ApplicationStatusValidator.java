package com.mpena.jobtrackerv2.validation;

import com.mpena.jobtrackerv2.model.ApplicationStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ApplicationStatusValidator implements ConstraintValidator<ValidApplicationStatus, String> {

    @Override
    public void initialize(ValidApplicationStatus constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        } else {
            return ApplicationStatus.contains(value.trim().toLowerCase());
        }
    }
    
}
