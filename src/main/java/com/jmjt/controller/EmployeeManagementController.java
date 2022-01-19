package com.jmjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.error.RecordNotFoundException;
import com.jmjt.model.Employee;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;
import com.jmjt.response.employee.EmployeeListResponse;
import com.jmjt.service.employee.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeManagementController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("")
	public ResponseEntity<EmployeeListResponse> fetchAllEmployees() {
		return ResponseEntity.status(200).body(new EmployeeListResponse(employeeService.fetchAllEmployees()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable String id) {
		Employee emp = null;
		try {
			emp = employeeService.findEmployeeById(id);
		} catch (RecordNotFoundException exception) {
			throw new RecordNotFoundException("Resource Not Found");
		}
		return ResponseEntity.status(200).body(emp);
	}

	@GetMapping("/{id}/usd")
	public ResponseEntity<Employee> findEmployeeByIdWithCurrency(@PathVariable String id) throws Exception {
		Employee emp = null;
		try {
			emp = employeeService.findEmployeeByIdWithCurrency(id);
		} catch (Exception exception) {
			throw new Exception("Failed To Execute");
		}
		return ResponseEntity.status(200).body(emp);
	}

	@PostMapping("")
	public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeCreateRequest employeeCreateRequest) {
		Employee emp = null;
		try {
			emp = employeeService.saveEmployee(employeeCreateRequest);
		} catch (Exception exception) {
			return ResponseEntity.status(417).body(emp);
		}
		return ResponseEntity.status(201).body(emp);
	}

	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody EmployeeUpdateRequest employeeUpdateRequest,
			@PathVariable String id) {
		Employee emp = null;
		try {
			emp = employeeService.updateEmployee(employeeUpdateRequest);
		} catch (Exception exception) {
			return ResponseEntity.status(417).body(emp);
		}
		return ResponseEntity.status(200).body(emp);
	}

	@GetMapping("/hike")
	public ResponseEntity<EmployeeListResponse> applySalaryIncrementToAll() {
		return ResponseEntity.status(200).body(new EmployeeListResponse(employeeService.applySalaryIncrementToAll()));
	}

	@GetMapping("/hike/{id}")
	public ResponseEntity<Employee> applySalaryIncrementById(@PathVariable String id) {
		Employee emp = null;
		try {
			emp = employeeService.applySalaryIncrementById(id);
		} catch (Exception exception) {
			return ResponseEntity.status(404).body(emp);
		}
		return ResponseEntity.status(200).body(emp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable String id) throws NotFoundException {
		try {
			employeeService.deleteEmployeeById(id);
		} catch (Exception exception) {
			return ResponseEntity.status(204).body("Failed To Delete");
		}
		return ResponseEntity.status(200).body("{Deleted Successfully}");
	}

	@GetMapping("/report")
	public ResponseEntity<String> generateEmployeesReport() throws InternalServerError {
			employeeService.generateEmployeesReport();
		
		return ResponseEntity.status(200).body("Generated Successfully");
	}

	@GetMapping("/report/{id}")
	public ResponseEntity<String> generateEmployeeReportById(@PathVariable String id) {
		try {
			employeeService.generateEmployeeReportById(id);
		} catch (Exception exception) {
			return ResponseEntity.status(404).body("Failed To generate report");
		}
		return ResponseEntity.status(200).body("Generated Successfully");
	}

}
