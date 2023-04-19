package com.esempio.RentalCar.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.xml.crypto.Data;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Date;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = dateValidator.class)
@Documented
public @interface PeriodoPrenotazioneValidator {
    String message() default "{DataFineValidator.PeriodoPrenotazione.Prenotazione.Validator}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
