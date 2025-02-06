package com.mpena.jobtrackerv2.validation;

import com.mpena.jobtrackerv2.model.Application;
import com.mpena.jobtrackerv2.model.ApplicationStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OfferForInterviewValidator implements ConstraintValidator<ValidOfferForInterview, Application> {

    @Override
    public void initialize(ValidOfferForInterview constraintAnnotation) {
        // // TODO Auto-generated method stub
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Application application, ConstraintValidatorContext context) {

        if (application.getStatus().equals("rejected") || 
            application.getStatus().equals("no response") ||
            application.getStatus() == null || application.getStatus().trim().isEmpty()
            && 
            (application.getOffer().equals("yes") || application.getOffer().equals("no"))) {
            return false;
        }

        if ("interview".equals(application.getStatus())) {
            String offer = application.getOffer();
            return offer == null || offer.trim().isEmpty() || "yes".equalsIgnoreCase(offer) || "no".equalsIgnoreCase(offer);
        }
        return false; 
    }
    
}
