package com.jmjt.request;

public class CreateRequest {

	private String name;

	public CreateRequest() {
		super();
	}

	public CreateRequest(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
