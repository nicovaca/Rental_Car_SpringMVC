package com.esempio.RentalCar.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = passwordStronger.class)
@Documented
public @interface passwordStrong {

    String message() default "{PasswordStrong.Utente.password.validator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}