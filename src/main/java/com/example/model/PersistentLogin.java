package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="persistent_logins")
public class PersistentLogin {
	
	@Id
	@GeneratedValue
	Integer id;
	
	@Column(nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String token;
	
	@Column(nullable=false)
	private String series;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Date getLast_used() {
		return last_used;
	}

	public void setLast_used(Date last_used) {
		this.last_used = last_used;
	}

	public Integer getId() {
		return id;
	}
	
	

}
