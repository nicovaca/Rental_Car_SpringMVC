package com.esempio.RentalCar.validator;

import com.esempio.RentalCar.entities.PeriodoPrenotazione;
import org.hibernate.validator.internal.util.annotation.ConstraintAnnotationDescriptor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class dateValidator implements ConstraintValidator<PeriodoPrenotazioneValidator, PeriodoPrenotazione> {





    @Override
    public void initialize(PeriodoPrenotazioneValidator periodoPrenotazioneValidator) {
    }

    @Override
    public boolean isValid(PeriodoPrenotazione periodoPrenotazione, ConstraintValidatorContext constraintValidatorContext) {
        return periodoPrenotazione.getDataInizio()!=null && periodoPrenotazione.getDataFine()!=null && periodoPrenotazione.getDataInizio().before(periodoPrenotazione.getDataFine());
    }



}
