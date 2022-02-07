package com.jmjt.service.employee;

import java.util.List;

import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.model.Employee;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;

public interface EmployeeService {

	List<Employee> fetchAllEmployees();

	Employee findEmployeeById(String id);

	Employee findEmployeeByIdWithCurrency(String id) throws InternalServerError;

	Employee applySalaryIncrementById(String id) throws NotFoundException;

	List<Employee> applySalaryIncrementToAll();

	Employee saveEmployee(EmployeeCreateRequest employeeCreateRequest) throws InternalServerError;

	void deleteEmployeeById(String employeeId) throws NotFoundException;

	Employee updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) throws NotFoundException ;

	void generateEmployeeReportById(String employeeId) throws InternalServerError ;

	void generateEmployeesReport() throws InternalServerError;

}