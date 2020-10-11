package com.example.service;

import com.example.model.Author;

public interface AuthorService {
	
	public Author findByFirstNameAndLastName(String firstName,String lastName);
	public void saveAuthor(Author author);

}
