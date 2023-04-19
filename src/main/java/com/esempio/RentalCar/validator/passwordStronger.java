package com.esempio.RentalCar.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class passwordStronger implements ConstraintValidator<passwordStrong,String> {

    private String password;

    @Override
    public void initialize(passwordStrong passwordStrong) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        return password.matches("[a-zA-Z0-9]*")
                && (password.length() > 4) && (password.length() < 14);
    }


}
