package com.mpena.jobtrackerv2.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = OfferForInterviewValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOfferForInterview {
    String message() default "Invalid offer value. When the value of status equals interview, offer property must be yes or no value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
