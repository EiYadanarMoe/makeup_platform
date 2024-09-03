package com.hostmdy.style.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class User {

	private Long id;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String role;
	private LocalDateTime createdAt;
	private Boolean enable = true;
	private String profilePicture;
	

	public User() {}

	public User(String firstname, String lastname, String username, String password, String email, String role,
			 Timestamp createdAt,String profilePicture) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.createdAt = LocalDateTime.now();
		this.profilePicture = profilePicture;
	}

	

	public User(Long id, String firstname, String lastname, String username, String password, String email, String role,
			Boolean enable,LocalDateTime createdAt,String profilePicture) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.enable = enable;
		this.createdAt = createdAt;
		this.profilePicture = profilePicture;
		
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	
	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	

	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	
	

	
	
	



}
