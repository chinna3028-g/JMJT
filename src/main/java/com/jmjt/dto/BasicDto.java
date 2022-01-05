package com.jmjt.dto;

public class BasicDto {
	private int id;
	private String name;

	public BasicDto() {
		super();
	}

	public BasicDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
