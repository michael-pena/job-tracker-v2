package com.mpena.jobtrackerv2.components.application.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * Custom annotation to validate that the accepted property has a valid value
 * when the offer property equals "yes" and the status property equals "inteview". 
 * The accepted property must be either "yes" or "no".
 *
 * <p>This annotation should be used on class level and is validated by the
 * {@link AcceptedValueValidator} class.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * @ValidAcceptedValue
 * public class JobOffer {
 *     private String offer;
 *     private String accepted;
 *     // getters and setters
 * }
 * }
 * </pre>
 *
 * @message The default error message to be used when validation fails.
 * @groups Allows the specification of validation groups, to which this constraint belongs.
 * @payload Can be used by clients of the Bean Validation API to assign custom payload objects to a constraint.
 */
@Constraint(validatedBy = AcceptedValueValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAcceptedValue {
    String message() default "Invalid accepted value. When offer equals yes, accepted property must also be a yes or no value";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
