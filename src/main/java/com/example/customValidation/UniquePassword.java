package com.example.customValidation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=UniquePasswordClass.class)
public @interface UniquePassword {
	
	String message() default "Izabrana lozinka vec postoji";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};

}
