package com.jmjt.request;

public class EmployeeUpdateRequest extends EmployeeCreateRequest {

	private String employeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
