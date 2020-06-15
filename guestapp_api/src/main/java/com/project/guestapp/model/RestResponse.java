package com.project.guestapp.model;

public class RestResponse {

	private String message;

	public RestResponse() {
		super();
	}

	public RestResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
