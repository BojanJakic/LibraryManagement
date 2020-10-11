package com.example.repository;

import org.springframework.data.jpa.repository.Query;

import com.example.model.Author;

public interface AuthorRepository extends BaseRepository<Author,Integer>{

@Query("SELECT a from Author a where a.firstName = ?1 and a.lastName = ?2")
public Author findByFirstNameAndLastName(String firstName,String lastName);

}
