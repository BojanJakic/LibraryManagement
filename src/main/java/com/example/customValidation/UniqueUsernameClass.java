package com.example.customValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.service.UserService;

public class UniqueUsernameClass implements ConstraintValidator<UniqueUsername,String>{

@Autowired 
private UserService userService;

@Override
public void initialize(UniqueUsername arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public boolean isValid(String username, ConstraintValidatorContext arg1) {
	
	return userService.existsByUsername(username);
}
}
