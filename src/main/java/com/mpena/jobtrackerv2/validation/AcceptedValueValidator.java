package com.mpena.jobtrackerv2.validation;

import com.mpena.jobtrackerv2.model.Application;
import com.mpena.jobtrackerv2.model.ApplicationStatus;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class AcceptedValueValidator implements ConstraintValidator<ValidAcceptedValue, Application> {

    @Override
    public void initialize(ValidAcceptedValue constraintAnnotation) {
        // // TODO Auto-generated method stub
        // ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Application application, ConstraintValidatorContext context) {

        if (application.getStatus().equals(ApplicationStatus.INTERVIEW.getValue()) && 
            application.getOffer().trim().toLowerCase().equals("yes")) {

            String accepted = application.getAccepted().trim().toLowerCase();
            return accepted.equals("yes") || accepted.equals("no"); 
        }
        
        return false;
    }
    
}
