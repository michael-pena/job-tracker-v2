package com.mpena.jobtrackerv2.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = AcceptedValueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAcceptedValue {
    String message() default "Invalid accepted value. When offer equals yes, accepted property must also be a yes or no value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
