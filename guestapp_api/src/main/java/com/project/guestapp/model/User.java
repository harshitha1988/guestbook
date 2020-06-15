package com.project.guestapp.model;

public class User {

	private String userName;
	
	private Integer userId;

	public User() {
		super();
	}

	public User(String name, int userId) {
		super();
		this.userName = name;
		this.userId = userId;
	}
	
	public User(String name) {
		super();
		this.userName = name;
	}
	
	public String getName() {
		return userName;
	}

	public void setName(String name) {
		this.userName = name;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
}
