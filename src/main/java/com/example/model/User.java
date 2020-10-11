package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.example.customValidation.UniqueEmail;
import com.example.customValidation.UniquePassword;
import com.example.customValidation.UniqueUsername;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@Table(name="user?table")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class User {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message="Pogresan unos")
	@Pattern(regexp = "^[a-zA-Z]{2,20}$",message="Pogresan unos")
	private String firstName;
	
	@NotBlank(message="Pogresan unos")
	@Pattern(regexp="^[a-zA-Z]{2,20}$",message="Pogresan unos")
	private String lastName;
	
	@NotBlank(message="Pogresan unos")
	@Pattern(regexp="^[a-zA-Z0-9]{5,20}$",message="Pogresan unos")
	@UniqueUsername
	private String username;
	
	@NotBlank(message="Pogresan unos")
	@Pattern(regexp="^(?=.*[A-Z].*[A-Z])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z]).{8,80}$"
	         ,message="Pogresan unos")
	@UniquePassword
	private String password;
	
	@NotBlank(message="Pogresan unos")
	@Pattern(regexp="^\\S+@\\S+\\.*\\S+$",message="Pogresan unos")
	@UniqueEmail
	private String email;
	
	private boolean enabled;
	
	private String role = "ROLE_USER";
	
	@ManyToMany
	@JoinTable(name="User_Book", 
               joinColumns=@JoinColumn(name="user_id"),
               inverseJoinColumns=@JoinColumn(name="book_id"))  
	private List<Book>listOfBooks = new ArrayList<Book>();
	
	
	public User(){}

	public Integer getId() {
		return id;
	}

//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
	

}
