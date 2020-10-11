package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Book;
import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	@Transactional
	public boolean existsByPassword(String password){
		return userRepo.existsByPassword(password);
	}
	
	@Override
	@Transactional
	public boolean existsByUsername(String username){
		
		return userRepo.existsByUsername(username);
	}
	
	@Override
	@Transactional
	public void saveUser(User user){
		user.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(user);
	}
	
	@Override
	@Transactional
	public User findUserByUsername(String username){
		return userRepo.findUserByUsername(username);
	}
	
	@Override
	@Transactional
	public List<User> findUserByFirstNameAndLastName(String fullname){
		return userRepo.findUserByFirstNameAndLastName(fullname);
	}
	
	@Override
	@Transactional
	public User findUserById(int id){
		return userRepo.findUserById(id);
	}

	@Override
	@Transactional
	public List<Book> findBooksByUsersUsername(String username) {
		return userRepo.findBooksByUsersUsername(username);
		
	}

	@Override
	@Transactional
	public List<User> findReservationByBookTitle(String title) {
		
		return userRepo.findReservationByBookTitle(title);
	}

	@Override
	@Transactional
	public void deleteUser(User user) {
	userRepo.delete(user);	
		
	}

	@Override
	@Transactional
	public List<User> findAllUsers() {
		
		return userRepo.findAll();
	}

	@Override
	@Transactional
	public void enableUserById(Integer id) {
		userRepo.enableUserById(id);
		
	}

	@Override
	public boolean existsByEmail(String email) {
		
		return userRepo.existsByEmail(email);
	}
	
	

}
