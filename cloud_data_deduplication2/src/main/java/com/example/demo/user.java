package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="health")
public class user {

	@Id
	private int id;
	private String username;
	private String firstname;
	private String lastname;

	
	private String password;
	private String email;
	private String role;
	private String alpha;
	
	
	public user() {
		
		// TODO Auto-generated constructor stub
	}
	public user(String username, String firstname, String lastname, String password, String email,
			String role) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		
		this.password = password;
		this.email = email;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				 +", password=" + password + ", email=" + email + ", role=" + role + "]";
	}
	
	public String getAlpha() {
		return alpha;
	}
	public void setAlpha(String alpha) {
		this.alpha = alpha;
	}
	

}
 