package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Book;
import com.example.model.User;
import com.example.service.BookService;
import com.example.service.UserService;

@Controller
public class ReservationController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@RequestMapping("/show_reserved_books")
	public @ResponseBody Map<String, List<Book>> getReservedBooks() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		Map<String, List<Book>> map = new HashMap<String, List<Book>>();

		List<Book> list = userService.findBooksByUsersUsername(username);
		map.put("book", list);

		return map;
	}

	@RequestMapping("/reserve_book")
	public @ResponseBody String reserve(@RequestParam("value") String title) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		String message = "";

		User user = userService.findUserByUsername(username);

		Book book = bookService.findByBookTitle(title);

		if (book == null) {
			message = "Nije pronadjena trazena knjiga";
		} else if (book.getAvailableInstances() == 0) {
			message = "Trazena knjiga nije trenutno dostupna";
		} else if (user.getListOfBooks().size() == 5) {
			message = "Vec imate pet rezervacija";
		} else {
			user.getListOfBooks().add(book);
			bookService.updateReservedBook(book.getId());
			message = "Uspijesno ste rezervisali knjigu";
		}

		return message;
	}

	@RequestMapping("/author_reservation")
	public @ResponseBody Map<String, List<Book>> authorNameReservation(@RequestParam("value") String fullname) {
       
		Map<String, List<Book>> map = new HashMap<String, List<Book>>();

		List<Book> list = bookService.findReservedBookByAuthorFullname(fullname);
		
		map.put("author_reservation", list);

		return map;
	}

	@RequestMapping("/fullname_reservation")
	public @ResponseBody Map<String, List<Book>> fullnameReservation(@RequestParam("value") String fullname) {

		Map<String, List<Book>> map = new HashMap<String, List<Book>>();

		List<Book> list = bookService.findReservationByUserFullname(fullname);

		map.put("user_reservation", list);

		return map;
	}

	@RequestMapping("/title_reservation")
	public @ResponseBody Map<String, List<User>> titleReservation(@RequestParam("value") String title) {

		Map<String, List<User>> map = new HashMap<String, List<User>>();

		List<User> list = userService.findReservationByBookTitle(title);

		map.put("title_reservation", list);

		return map;
	}

	@RequestMapping("/delete_reservation")
	public @ResponseBody String cancelBook(@RequestParam("userId") String id, @RequestParam("title") String title) {

		String message = "";
		boolean boo = false;

		User user = userService.findUserById(Integer.parseInt(id));

		Book book = bookService.findByBookTitle(title);

		if (user == null) {
			message = "Korisnik nije pronadjen";
		} else if (book == null) {
			message = "Knjiga nije pronadjena";
		} else {

			List<Book> list = user.getListOfBooks();

			for (Book b : list) {
				if (b.equals(book)) {
					list.remove(book);
					bookService.updateReturnedBook(title);
					boo = true;
					break;
				}
			}
			if (boo) {
				message = "Rezervacija je uspijesno otkazana";
			} else {
				message = "Nepostojeca rezervacija, prekontrolisite unos !";
			}
		}
		return message;
	}

	@RequestMapping("/username_reservation")
	public @ResponseBody Map<String, List<Book>> usernameReservation(@RequestParam("value") String username) {

		Map<String, List<Book>> map = new HashMap<String, List<Book>>();

		List<Book> list = bookService.findReservationByUsername(username);

		map.put("user_reservation", list);

		return map;
	}

}
