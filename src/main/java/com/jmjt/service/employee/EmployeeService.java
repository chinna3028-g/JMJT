package com.jmjt.service.employee;

import java.util.List;

import com.jmjt.error.InternalServerError;
import com.jmjt.model.Employee;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;

public interface EmployeeService {

	List<Employee> fetchAllEmployees();

	Employee findEmployeeById(String id) throws InternalServerError;

	Employee findEmployeeByIdWithCurrency(String id) throws InternalServerError;

	Employee applySalaryIncrementById(String id) throws InternalServerError;

	List<Employee> applySalaryIncrementToAll();

	Employee saveEmployee(EmployeeCreateRequest employeeCreateRequest) throws InternalServerError;

	void deleteEmployeeById(String employeeId) throws InternalServerError;

	Employee updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) throws InternalServerError;

	void generateEmployeeReportById(String employeeId) throws InternalServerError;

	void generateEmployeesReport() throws InternalServerError;

}