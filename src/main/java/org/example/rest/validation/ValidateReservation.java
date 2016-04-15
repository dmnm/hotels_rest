package org.example.rest.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, PARAMETER, TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ReservationValidator.class)
public @interface ValidateReservation {
    String message() default "{org.example.rest.validation.reservation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
