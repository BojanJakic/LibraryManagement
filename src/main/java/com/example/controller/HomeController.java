package com.example.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Author;
import com.example.model.Book;
import com.example.model.User;
import com.example.service.UserService;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@RequestMapping("/")
	public String index() {

		return "index";
	}

	@RequestMapping("/log")
	public String goToLogin() {

		return "index";
	}

	@RequestMapping("/errorLogin")
	public String loginError(Model mod) {
		
		mod.addAttribute("loginError", true);
		return "index";
	}

	@RequestMapping("/logged")
	public String logged(HttpServletRequest request, Principal pri) {
		String url = "";

		if (request.isUserInRole("ROLE_USER")) {
			url = "logged_user_interface";
		} else if (request.isUserInRole("ROLE_ADMIN")) {
			url = "admin_interface";
		}

		return url;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null){
		new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@RequestMapping("/modal")
	public String getModal() {
		System.out.println("server");
		return "fragment/registartion_book_form";
	}

	@ModelAttribute("newUser")
	public User getUser() {
		System.out.println("user home");
		return new User();
	}
	
	@RequestMapping("/include_fragment")
	public  String fragment(@RequestParam("fragment")String fragment){
		return "fragment/searcher :: " + fragment;
	}

}
