package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Book;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.UserService;

@Controller
public class BookController {
	
//	@Autowired
//	private UserService userService;

	@Autowired
	private BookService bookService;

//	@Autowired
//	private AuthorService authorService;
	
	@RequestMapping(value = "/find_by_author",method=RequestMethod.GET)
	public @ResponseBody Map<String,List<Book>> authorName(@RequestParam("value")String fullName){
		
		Map<String,List<Book>>map = new HashMap<String,List<Book>>();
		
		List<Book> list = new ArrayList<Book>();
		list = bookService.findBookByAuthorName(fullName);
		
		map.put("book", list);
		
		return map;
	}
	
	@RequestMapping(value = "/available_books",method=RequestMethod.GET)
	public @ResponseBody Map<String,List<Book>> availableBook(){
		
		Map<String,List<Book>>map = new HashMap<String,List<Book>>();
		
		List<Book>list = new ArrayList<Book>();
		list = bookService.findAvailableBooks();
		
		map.put("book", list);
		
		return map;
	}
	
	@RequestMapping("/all_books")
	public @ResponseBody Map<String,List<Book>> allBooks(){
		
		Map<String,List<Book>> map = new HashMap<String,List<Book>>();
		
		List<Book>list = new ArrayList<Book>();
		list = bookService.findAll();
		
		map.put("book", list);
		return map;
	}
	
	@RequestMapping("/find_by_book_title")
	public @ResponseBody Map<String,Book> bookTitle(@RequestParam("value")String title){
		
		Map<String,Book> map = new HashMap<String,Book>();
		
		Book book = bookService.findByBookTitle(title);
		
		map.put("book", book);
		
		return map;
		
	}

}
