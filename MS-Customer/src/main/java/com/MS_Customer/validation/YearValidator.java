//package com.MS_Customer.validation;
//
//import jakarta.validation.ConstraintValidator;
//import jakarta.validation.ConstraintValidatorContext;
//
//import java.time.LocalDate;
//
//public class YearValidator implements ConstraintValidator<ValidYear, LocalDate> {
//    private int min;
//    private int max;
//
//    @Override
//    public void initialize(ValidYear constraintAnnotation) {
//        this.min = constraintAnnotation.min();
//        this.max = constraintAnnotation.max();
//    }
//
//    @Override
//    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
//        if (value == null) {
//            return true;
//        }
//        int year = value.getYear();
//        return year >= min && year <= max;
//    }
//}
