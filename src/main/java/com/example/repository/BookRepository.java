package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.model.Author;
import com.example.model.Book;

public interface BookRepository extends BaseRepository<Book,Integer>{
	
	public List<Book> findAll();
	
	@Query("SELECT b FROM Book b JOIN b.author a WHERE b.title = ?1 AND a.firstName = ?2 AND a.lastName = ?3)")
	public Book findBookByNameAndAuthor(String bookName,String authorFirstName,String authorLastName);
		
	@Modifying
	@Query("UPDATE Book b SET b.totalInstances = (b.totalInstances + ?1),b.availableInstances = (b.availableInstances + ?1) WHERE b.title = ?2 AND b.author = ?3")
	public void updateNumberOfBookInstances(int count,String title,Author author);

    @Query("SELECT b FROM Book b WHERE b.availableInstances > 0")
    public List<Book> findAvailableBooks();
    
    @Query("SELECT b FROM Book b JOIN b.author a WHERE CONCAT(a.firstName,a.lastName) = ?1")
    public List<Book> findBookByAuthorName(String fullName);
    
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    public Book findByBookTitle(String title);
    
    @Modifying
    @Query("UPDATE Book b SET b.availableInstances = (b.availableInstances - 1) WHERE b.id = ?1")
    public void updateReservedBook(int id);
    
    @Query("SELECT book FROM Book book JOIN book.author b WHERE CONCAT(b.firstName,b.lastName) = ?1 AND (book.totalInstances - book.availableInstances) > 0")
    public List<Book> findReservedBookByAuthorFullname(String fullname);
    
    @Query("SELECT b FROM User u JOIN u.listOfBooks b WHERE (b.totalInstances - b.availableInstances) > 0 AND u.username = ?1")
    public List<Book> findReservationByUsername(String username);
    
    @Query("SELECT b FROM User u JOIN u.listOfBooks b WHERE (b.totalInstances - b.availableInstances) > 0 AND CONCAT(u.firstName,u.lastName) = ?1")
    public List<Book> findReservationByUserFullname(String fullname);
    
    @Modifying
    @Query("UPDATE Book b SET b.availableInstances = (b.availableInstances + 1) WHERE b.title = ?1")
    public void updateReturnedBook(String title);

}
