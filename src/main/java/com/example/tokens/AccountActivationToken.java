package com.example.tokens;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.example.model.User;

@Entity
public class AccountActivationToken {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String token;
	
	@OneToOne
	private User user;
	
	private Date created;
	
	public AccountActivationToken(){}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getId() {
		return id;
	}
	
	

}
