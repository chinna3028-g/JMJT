package com.jmjt.service.employee;

import java.util.List;

import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.error.RecordNotFoundException;
import com.jmjt.model.Employee;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;

public interface EmployeeService {

	List<Employee> fetchAllEmployees();

	Employee findEmployeeById(String id) throws RecordNotFoundException;

	Employee findEmployeeByIdWithCurrency(String id) throws InternalServerError, RecordNotFoundException;

	Employee applySalaryIncrementById(String id) throws NotFoundException, RecordNotFoundException;

	List<Employee> applySalaryIncrementToAll();

	Employee saveEmployee(EmployeeCreateRequest employeeCreateRequest) throws InternalServerError ;

	void deleteEmployeeById(String employeeId) throws NotFoundException, RecordNotFoundException;

	Employee updateEmployee(EmployeeUpdateRequest employeeUpdateRequest) throws NotFoundException, RecordNotFoundException ;

	void generateEmployeeReportById(String employeeId) throws InternalServerError, RecordNotFoundException ;

	void generateEmployeesReport() throws InternalServerError;

}