package com.example.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.service.UserService;

public class UniquePasswordClass implements ConstraintValidator<UniquePassword,String>{

	@Autowired
	private UserService userService;
	
	
	@Override
	public void initialize(UniquePassword arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext arg1) {
		
		return userService.existsByPassword(password);
	}
}
