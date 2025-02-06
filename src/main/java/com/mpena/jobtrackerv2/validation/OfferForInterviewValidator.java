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

        if (application.getStatus().equals(ApplicationStatus.REJECTED.getValue()) || 
            application.getStatus().equals(ApplicationStatus.NO_RESPONSE.getValue()) ) {
                String offer = application.getOffer().trim().toLowerCase();

            if ( !offer.isEmpty() || offer != null) {
                return false;
            }
        }

        if (application.getStatus().equals(ApplicationStatus.INTERVIEW.getValue())) {
            String offer = application.getOffer();
            if (offer == null) {
                return false;
            }
            return offer.equals("yes") || offer.equals("no");
        }
        return true;
    }
    
}
