package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Author;
import com.example.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepo;
	
	@Override
	@Transactional
	public Author findByFirstNameAndLastName(String firstName,String lastName){
		return authorRepo.findByFirstNameAndLastName(firstName, lastName);
	}
	
	@Override
	@Transactional
	public void saveAuthor(Author author){
		authorRepo.save(author);
	}

}
