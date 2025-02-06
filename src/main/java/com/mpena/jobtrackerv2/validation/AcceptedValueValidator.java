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

        if (application.getAccepted() != null)
        {
            if ("interview".equals(application.getStatus()) && "yes".equalsIgnoreCase(application.getOffer())) {
                String accepted = application.getAccepted();
                return accepted == null || accepted.trim().isEmpty() || "yes".equalsIgnoreCase(accepted) || "no".equalsIgnoreCase(accepted);
            }

        }

        return true; // If status is not "interview" or offer is not "yes", no validation needed for accepted
    }
    
}
