package com.jmjt.response.employee;

import com.jmjt.request.EmployeeCreateRequest;

public class EmployeeCreateResponse extends EmployeeCreateRequest {

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
