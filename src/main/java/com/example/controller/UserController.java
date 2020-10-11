package com.example.controller;

import java.util.ArrayList;
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
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
  @RequestMapping("/remove_user")
  public @ResponseBody String deleteUser(@RequestParam("value")String id){
	 User user = userService.findUserById(Integer.parseInt(id));
	 
	 String message = "";
	 if(user == null){
		message = "Korisnik nije pronadjen";
	 }else{
		 userService.deleteUser(user);
		 message  = "Korisnik je izbrisan";
	 }
	 
	 return message;
  }
  
  @RequestMapping("/all_users")
  public @ResponseBody Map<String,List<User>> getAll(){
	  
	  Map<String,List<User>>map = new HashMap<String,List<User>>();
	  
	  List<User>list = userService.findAllUsers();
	  
	  map.put("user",list);
	  
	  return map;
  }
  
  @RequestMapping("/find_by_username")
	public @ResponseBody Map<String,User> byUsername(@RequestParam("value")String username){
		
		Map<String,User>map = new HashMap<String,User>();
		
		User u = userService.findUserByUsername(username);
		
		map.put("user",u);
		
		return map;
	}
	
	@RequestMapping("/find_by_fullname")
	public @ResponseBody Map<String,List<User>> byFullname(@RequestParam("value")String fullname){
		
		Map<String,List<User>>map = new HashMap<String,List<User>>();
		
		List<User>list = new ArrayList<User>();
		list = userService.findUserByFirstNameAndLastName(fullname);
		
		map.put("user", list);
		
		return map;
		
	}
	
	@RequestMapping("/find_by_id")
	public @ResponseBody Map<String,User> byId(@RequestParam("value")String id){
		Map<String,User>map = new HashMap<String,User>();
		
		User u = userService.findUserById(Integer.parseInt(id));
		
		map.put("user", u);
		
		return map;
		
	}
  
  

}
