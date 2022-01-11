package com.jmjt.mapper;

import org.springframework.stereotype.Component;

import com.jmjt.model.Basic;
import com.jmjt.model.Employee;
import com.jmjt.request.CreateRequest;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;
import com.jmjt.request.UpdateRequest;

@Component
public class Mapper {

	public Basic mapCreateRequest(CreateRequest createRequest) {
		Basic basic = new Basic();
		basic.setName(createRequest.getName());
		return basic;
	}

	public Basic mapUpdateRequest(UpdateRequest updateRequest) {
		Basic basic = new Basic();
		basic.setName(updateRequest.getName());
		return basic;
	}

	public Employee mapEmployeeCreateRequest(EmployeeCreateRequest employeeCreateRequest) {
		Employee emp = new Employee();
		emp.setEmployeeName(employeeCreateRequest.getEmployeeName());
		emp.setEmployeeDOB(employeeCreateRequest.getEmployeeDOB());
		emp.setEmployeeSalary(employeeCreateRequest.getEmployeeSalary());
		emp.setEmployeeDesignation(employeeCreateRequest.getEmployeeDesignation());
		return emp;
	}

	public Employee mapEmployeeUpdateRequest(EmployeeUpdateRequest employeeupdateRequest) {
		Employee emp = new Employee();
		emp.setId(employeeupdateRequest.getEmployeeId());
		emp.setEmployeeName(employeeupdateRequest.getEmployeeName());
		emp.setEmployeeDOB(employeeupdateRequest.getEmployeeDOB());
		emp.setEmployeeSalary(employeeupdateRequest.getEmployeeSalary());
		emp.setEmployeeDesignation(employeeupdateRequest.getEmployeeDesignation());
		return emp;
	}

}
