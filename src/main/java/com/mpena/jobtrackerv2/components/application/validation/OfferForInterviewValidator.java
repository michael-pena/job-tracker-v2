package com.mpena.jobtrackerv2.components.application.validation;

import com.mpena.jobtrackerv2.components.application.model.Application;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OfferForInterviewValidator implements ConstraintValidator<ValidOfferForInterview, Application> {

    @Override
    public void initialize(ValidOfferForInterview constraintAnnotation) {
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Application application, ConstraintValidatorContext context) {

        String status = application.getStatus().trim().toLowerCase();
        String offer = application.getOffer();

        if ( (status.equals("no response") || status.equals("rejected")) && (offer == null || offer.isEmpty())) {
            return true;
        }

        if (status.equals("interview") && (offer == null) || offer.isEmpty()) {
            return false;
        }

        if ( (status.equals("interview") ) && 
            (offer.equals("yes") || offer.equals("no"))) {
            return true;
        }

        return false;
    }
    
}
