package com.mpena.jobtrackerv2.components.application.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate that the offer property has a valid value when the status is set to interview.
 * 
 * <p>This annotation ensures that when the status equals "interview", the offer property must be either "yes" or "no".</p>
 * 
 * <p>Usage example:</p>
 * <pre>
 * &#64;ValidOfferForInterview
 * public class JobApplication {
 *     private String status;
 *     private String offer;
 *     // getters and setters
 * }
 * </pre>
 * 
 * <p>Attributes:</p>
 * <ul>
 *   <li><b>message</b>: The error message to be displayed when the validation fails. Default is "Invalid offer value. When the value of status equals interview, offer property must be yes or no value".</li>
 *   <li><b>groups</b>: Allows the specification of validation groups to which this constraint belongs.</li>
 *   <li><b>payload</b>: Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint.</li>
 * </ul>
 * 
 * <p>Annotation parameters:</p>
 * <ul>
 *   <li><b>validatedBy</b>: Specifies the validator class that will handle the validation logic. In this case, it is {@code OfferForInterviewValidator.class}.</li>
 *   <li><b>target</b>: Indicates that this annotation can be applied to types (classes, interfaces, etc.).</li>
 *   <li><b>retention</b>: Specifies that this annotation will be available at runtime.</li>
 * </ul>
 */
@Constraint(validatedBy = OfferForInterviewValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOfferForInterview {
    String message() default "Invalid offer value. When the value of status equals interview, offer property must be yes or no value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
