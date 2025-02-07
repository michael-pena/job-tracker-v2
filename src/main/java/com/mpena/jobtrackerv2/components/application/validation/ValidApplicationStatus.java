package com.mpena.jobtrackerv2.components.application.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate the application status field.
 * This annotation ensures that the application status is one of the following values:
 * "rejected", "no response", or "interview".
 * 
 * Usage:
 * 
 * <pre>
 * {@code
 * @ValidApplicationStatus
 * private String status;
 * }
 * </pre>
 * 
 * The validation logic is implemented in the {@link ApplicationStatusValidator} class.
 * 
 * Attributes:
 * - message: The error message to be returned if the validation fails. Default is "Application status must be either: rejected, no response, interview".
 * - groups: Allows specification of validation groups to which this constraint belongs.
 * - payload: Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint.
 * 
 * Annotations:
 * - @Constraint: Specifies the validator class that will handle the validation logic.
 * - @Target: Indicates the contexts in which this annotation can be applied (methods, fields, annotation types, parameters).
 * - @Retention: Specifies that this annotation will be available at runtime.
 */
@Constraint(validatedBy = ApplicationStatusValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidApplicationStatus {
    String message() default "Application status must be either: rejected, no response, interview";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
