package com.example.customValidation;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniqueEmailClass.class)
public @interface UniqueEmail {
	
	String message() default "Vec postoji nalog sa unesenom email adresom";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}
