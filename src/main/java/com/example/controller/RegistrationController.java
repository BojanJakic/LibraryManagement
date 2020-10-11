package com.example.controller;

import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.email.MyEmailSender;
import com.example.model.Author;
import com.example.model.Book;
import com.example.model.User;
import com.example.service.AccountActivationTokenService;
import com.example.service.AuthorService;
import com.example.service.BookService;
import com.example.service.UserService;
import com.example.tokens.AccountActivationToken;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@Autowired
	private AccountActivationTokenService activationService;

	@Autowired
	private MyEmailSender sender;

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorService authorService;
	
	@ModelAttribute("newBook")
	public Book newBook() {
     return new Book();
	}

	@ModelAttribute("author")
	public Author author() {
		return new Author();
	}

	@ModelAttribute("newUser")
	public User getUser() {
		return new User();
	}

	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	public @ResponseBody boolean username(@RequestParam(name = "credentials") String username) {

		boolean boo = userService.existsByUsername(username);
		
		return boo;
	}

	@RequestMapping(value = "/checkPassword", method = RequestMethod.GET)
	public @ResponseBody boolean password(@RequestParam(name = "credentials") String password) {

		boolean boo = userService.existsByPassword(password);
		return boo;
	}

	@RequestMapping("/checkEmail")
	public @ResponseBody boolean email(@RequestParam(name = "credentials") String email) {
		boolean boo = userService.existsByEmail(email);
		return boo;
	}

	@RequestMapping(value = "/register_new_user_ajax", method = RequestMethod.POST)
	public @ResponseBody String newUserAjax(@RequestBody User user) throws MessagingException {

		String message = "Uspijesno ste se registrovali,da biste koristili vas racun kliknite na link koji vam je poslat putem email-a";
        user.setRole("ROLE_ADMIN");
		userService.saveUser(user);

		AccountActivationToken activate = new AccountActivationToken();
		String token = UUID.randomUUID().toString();

		activate.setCreated(new Date());
		activate.setToken(token);
		activate.setUser(user);

		activationService.saveToken(activate);

		String email = sender.prepareRegistrationMessage(token, user.getFirstName(), user.getLastName());
		sender.sendMessage(user.getEmail(), email, "Registracija");

		return message;
	}
	
	@RequestMapping("/user_registration")
	public String goToRegistrationPage() {
       return "registration";
	}

	@RequestMapping(value = "/register_new_user", method = RequestMethod.POST)
	public String newUser(@Valid @ModelAttribute("newUser") User user, BindingResult result, Model model) {

		String message = "Uspijesno ste se registrovali";

		if (!result.hasErrors()) {
			userService.saveUser(user);
			model.addAttribute("notification", message);
		}

		return "registration";

	}

	@RequestMapping("/book_registration")
	public String bookForm() {
		return "book_registration";
	}

	@RequestMapping(value = "/addBook")
	public String registerBook(@Valid @ModelAttribute("newBook") Book book, BindingResult res1,
			@Valid @ModelAttribute("author") Author author, BindingResult res2, Model model,
			@RequestParam("count") String count) {

		if (!res1.hasErrors() && !res2.hasErrors()) {

			Book b = bookService.findBookByNameAndAuthor(book.getTitle(), author.getFirstName(), author.getLastName());
			Author checkAuthor = authorService.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());

			if (b == null) {

				if (checkAuthor == null) {
					authorService.saveAuthor(author);
					book.setAuthor(author);
				} else {
					book.setAuthor(checkAuthor);
				}

				book.setTotalInstances(Integer.parseInt(count));
				book.setAvailableInstances(Integer.parseInt(count));
				bookService.saveBook(book);

			} else {

				bookService.updateNumberOfBookInstances(Integer.parseInt(count), b.getTitle(), checkAuthor);
			}

			model.addAttribute("message", true);
		}
		return "book_registration";

	}

	@RequestMapping("/account_activation")
	public String activation(@RequestParam("param") String token) throws MessagingException {

		String message = "";

		AccountActivationToken tok = activationService.findByToken(token);
		User user = tok.getUser();

		if (user.isEnabled()) {
			return "index";
		}
		
        Date created = tok.getCreated();

		if ((new Date().getTime() - created.getTime()) > 86400000) {

			String newToken = UUID.randomUUID().toString();
			activationService.updateToken(new Date(), newToken, tok.getId());

			message = "Aktivacioni link nije vise aktivan, poslali smo vam novi";
			String email = sender.resendLinkMessage(tok.getToken());
			sender.sendMessage(tok.getUser().getEmail(), email, "Novi aktivacioni link");

		} else {
			userService.enableUserById(user.getId());
			message = "Uspijesno ste aktivirali racun";
			System.out.println(message);
		}

		return "index";

	}

}
