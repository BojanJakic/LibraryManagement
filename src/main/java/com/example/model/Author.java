package com.example.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Pattern;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="id")
public class Author implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotBlank(message="Popunite polje")
	@Pattern(regexp="^[a-zA-Z]{2,30}$",message="Ime sadrzi nedozvoljen karakter(e)")
	private String firstName;
	
	@NotBlank(message="Popunite polje")
	@Pattern(regexp="^[a-zA-Z\\s-]{2,30}$",message="Ime sadrzi nedozvoljen karakter(e)")
	private String lastName;
	
	@OneToMany(mappedBy="author")
	//@JsonBackReference
	@JsonIgnore
	private List<Book>list = new ArrayList<Book>();
	
	public Author(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public List<Book> getList() {
		return list;
	}

	public void setList(List<Book> list) {
		this.list = list;
	}
	
	

}
