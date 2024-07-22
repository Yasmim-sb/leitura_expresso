//package com.MS_Customer.validation;
//
//import jakarta.validation.Constraint;
//import jakarta.validation.Payload;
//
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//
//@Constraint(validatedBy = YearValidator.class)
//@Target({ElementType.FIELD})
//@Retention(RetentionPolicy.RUNTIME)
//public @interface ValidYear {
//
//    String message() default "Ano inválido";
//    Class<?>[] groups() default {};
//    Class<? extends Payload>[] payload() default {};
//    int min() default 1900;
//    int max() default 2004;
//}
