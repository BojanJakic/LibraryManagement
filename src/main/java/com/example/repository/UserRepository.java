package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Book;
import com.example.model.User;


public interface UserRepository extends BaseRepository<User,Integer>{
	
	@Query("SELECT CASE WHEN COUNT(user) > 0 THEN 'false' ELSE 'true' END  FROM User user WHERE user.password = ?1")
	public boolean existsByPassword(String password);
	
	
	@Query("SELECT CASE WHEN COUNT(user) > 0 THEN 'false' ELSE 'true' END  FROM User user WHERE user.username = ?1")
	public boolean existsByUsername(String username);
	
	@Query("SELECT CASE WHEN COUNT(user) > 0 THEN 'false' ELSE 'true' END FROM User user  WHERE user.email = ?1")
	public boolean existsByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	public User findUserByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE CONCAT(u.firstName,u.lastName) = ?1")
	public List<User> findUserByFirstNameAndLastName(String fullname);
	
	@Query("SELECT u FROM User u WHERE u.id = ?1")
	public User findUserById(int id);
	
	@Query("SELECT book FROM User user  JOIN user.listOfBooks book WHERE user.username = ?1")
	public List<Book> findBooksByUsersUsername(String username);
	
	@Query("SELECT u FROM User u JOIN u.listOfBooks b WHERE b.title = ?1")
    public List<User> findReservationByBookTitle(String title);
	
	@Modifying
	@Query("UPDATE User user SET user.enabled = 1 WHERE user.id = ?1")
	public void enableUserById(Integer id);
	
}
