package com.example.service;

import java.util.List;

import com.example.model.Author;
import com.example.model.Book;

public interface BookService {
	
	public Book findBookByNameAndAuthor(String bookName,String authorFirstName,String authorLastName);
    public void saveBook(Book book);
    public void updateNumberOfBookInstances(int count,String title,Author author);
    public List<Book> findAll();
    public List<Book> findAvailableBooks();
    public List<Book> findBookByAuthorName(String fullName);
    public Book findByBookTitle(String title);
    public void updateReservedBook(int id);
    public List<Book> findReservedBookByAuthorFullname(String fullname);
    public List<Book> findReservationByUsername(String username);
    public List<Book> findReservationByUserFullname(String fullname);
    public void updateReturnedBook(String title);
}
