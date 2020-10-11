package com.example.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.UserService;

public class UniqueEmailClass implements ConstraintValidator<UniqueEmail,String>{

	@Autowired
	private UserService userService;
	
	@Override
	public void initialize(UniqueEmail arg0) {
		
		
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext arg1) {
		
		return userService.existsByEmail(email);
	}

}
