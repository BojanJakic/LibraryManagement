package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Book implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message="Popunite polje")
	@Pattern(regexp="^[a-zA-Z\\s]{3,100}$",message="Naslov sadrzi nedozvoljen karakter")
	private String title;
	
	private int totalInstances;
	private int availableInstances;
	
	
	@ManyToOne
	@JoinColumn(name="author_id")
	//@JsonManagedReference
	private Author author;
	
	
	@ManyToMany(mappedBy="listOfBooks")
	@JsonIgnore
	private List<User>usersList = new ArrayList<User>();
	
	public Book(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTotalInstances() {
		return totalInstances;
	}

	public void setTotalInstances(int totalInstances) {
		this.totalInstances = totalInstances;
	}

	public int getAvailableInstances() {
		return availableInstances;
	}

	public void setAvailableInstances(int availableInstances) {
		this.availableInstances = availableInstances;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}
	
	
	

}
