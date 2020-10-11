package com.example.service;

import java.util.List;

import com.example.model.Book;
import com.example.model.User;

public interface UserService {
	
	public boolean existsByPassword(String password);
	public boolean existsByUsername(String username);
	public boolean existsByEmail(String email);
	public void saveUser(User user);
	public User findUserByUsername(String username);
	public List<User>findUserByFirstNameAndLastName(String fullname);
	public User findUserById(int id);
	public List<Book> findBooksByUsersUsername(String username);
	public List<User> findReservationByBookTitle(String title);
	public void deleteUser(User user);
	public List<User> findAllUsers();
	
	public void enableUserById(Integer id);

}
