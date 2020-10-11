package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Author;
import com.example.model.Book;
import com.example.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepo;
	
	
	@Override
	@Transactional
	public Book findBookByNameAndAuthor(String bookName,String authorFirstName,String authorLastName){
		return  bookRepo.findBookByNameAndAuthor(bookName, authorFirstName, authorLastName);
	}
	
	@Override
	@Transactional
	public void saveBook(Book book){
		bookRepo.save(book);
	}
	
	@Override
	@Transactional
	public void updateNumberOfBookInstances(int count,String title,Author author){
		bookRepo.updateNumberOfBookInstances(count, title, author);
	}
    @Override
    @Transactional
    public List<Book> findAll(){
    	return bookRepo.findAll();
    }
    
    @Override
    @Transactional
    public List<Book> findAvailableBooks(){
    	return bookRepo.findAvailableBooks();
    }
    
    @Override
    @Transactional
    public List<Book> findBookByAuthorName(String fullName){
    	return bookRepo.findBookByAuthorName(fullName);
    }
    
    @Override
    @Transactional
    public Book findByBookTitle(String title){
    	return bookRepo.findByBookTitle(title);
    }
    
    @Override
    @Transactional
    public void updateReservedBook(int id){
    	bookRepo.updateReservedBook(id);
    }

	@Override
	@Transactional
	public List<Book> findReservedBookByAuthorFullname(String fullname) {
		
		return bookRepo.findReservedBookByAuthorFullname(fullname);
	}

	@Override
	@Transactional
	public List<Book> findReservationByUsername(String username) {
		return bookRepo.findReservationByUsername(username);
	}

	@Override
	@Transactional
	public List<Book> findReservationByUserFullname(String fullname) {
		return bookRepo.findReservationByUserFullname(fullname);
	}

	@Override
	@Transactional
	public void updateReturnedBook(String title) {
		bookRepo.updateReturnedBook(title);
		
	}

	
}
