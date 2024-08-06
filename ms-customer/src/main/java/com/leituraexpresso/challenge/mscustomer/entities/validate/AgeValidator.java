package com.leituraexpresso.challenge.mscustomer.entities.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext constraintValidatorContext) {
        if(birthdate == null){
            return true;
        }
        int age = Period.between(birthdate, LocalDate.now()).getYears();
        return age >= 16;
    }
}
