package com.jmjt.response.employee;

import java.util.List;

import com.jmjt.model.Employee;

public class EmployeeListResponse {

	private List<Employee> employees;

	public EmployeeListResponse(List<Employee> employees) {
		super();
		this.employees = employees;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
