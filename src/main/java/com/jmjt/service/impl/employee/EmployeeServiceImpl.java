package com.jmjt.service.impl.employee;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmjt.dao.EmployeeRepository;
import com.jmjt.error.NotFoundException;
import com.jmjt.error.RecordNotFoundException;
import com.jmjt.mapper.Mapper;
import com.jmjt.model.Employee;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;
import com.jmjt.service.employee.EmployeeService;
import com.jmjt.util.JMJTUtil;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private JMJTUtil util;

	@Override
	public List<Employee> fetchAllEmployees() {
		List<Employee> listEmployees = employeeRepository.findAll();
		return listEmployees;
	}

	@Override
	public Employee findEmployeeById(String id) throws RecordNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);

		if (!employee.isPresent()) {
			throw new RecordNotFoundException("Resource Not Found");
		}
		return employee.get();
	}

	@Override
	public Employee applySalaryIncrementById(String id) throws NotFoundException {
		Employee employee = findEmployeeById(id);
		int increment = 0;
		int sal = employee.getEmployeeSalary() != null ? Integer.parseInt(employee.getEmployeeSalary()) : 0;
		if (sal <= 15000) {
			// incrementing salary 5%
			increment += (sal * 5) / 100;
			sal = sal + increment;

			employee.setEmployeeSalary(String.valueOf(sal));

		} else if (sal > 15000 && sal < 25000) {
			// incrementing salary 4%
			increment += (sal * 4) / 100;
			sal = sal + increment;

			employee.setEmployeeSalary(String.valueOf(sal));
		} else {
			// incrementing salary 3%
			increment += (sal * 3) / 100;
			sal = sal + increment;
			employee.setEmployeeSalary(String.valueOf(sal));
		}

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> applySalaryIncrementToAll() {
		List<Employee> listEmployees = employeeRepository.findAll();
		for (Employee employee : listEmployees) {
			int increment = 0;
			int sal = employee.getEmployeeSalary() != null ? Integer.parseInt(employee.getEmployeeSalary()) : 0;
			if (sal < 15000) {
				// incrementing salary 5%
				increment += (sal * 5) / 100;
				sal = sal + increment;

				employee.setEmployeeSalary(String.valueOf(sal));

			} else if (sal >= 15000 & sal <= 25000) {
				// incrementing salary 4%
				increment += (sal * 4) / 100;
				sal = sal + increment;

				employee.setEmployeeSalary(String.valueOf(sal));
			} else {
				// incrementing salary 3%
				increment += (sal * 3) / 100;
				sal = sal + increment;
				employee.setEmployeeSalary(String.valueOf(sal));
			}

			employeeRepository.save(employee);
		}
		return employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(EmployeeCreateRequest employeeCreateRequest) throws Exception {

		Employee employee = employeeRepository.save(mapper.mapEmployeeCreateRequest(employeeCreateRequest));
		if (employee.getId() == null) {
			throw new Exception("Not Able to Save Data");
		}

		return employee;
	}

	@Override
	public Employee updateEmployee(EmployeeUpdateRequest updateEmployeeRequest) throws NotFoundException {
		findEmployeeById(updateEmployeeRequest.getEmployeeId());
		return employeeRepository.save(mapper.mapEmployeeUpdateRequest(updateEmployeeRequest));
	}

	@Override
	public void deleteEmployeeById(String id) throws NotFoundException {
		findEmployeeById(id);
		employeeRepository.deleteById(String.valueOf(id));
	}

	@Override
	public void generateEmployeeReportById(String employeeId) throws Exception {
		Employee employee = findEmployeeById(employeeId);

		PrintWriter writer = null;
		try {
			File file = new File(util.getFileName());
			writer = new PrintWriter(file);
			writer.write(
					"Employee Id\t\t\t\t\tEmployee Name\t\t\t\tEmployee Desognation\tEmployee DOB\tEmployee Salary");
			writer.println();

			writer.write(employee.getId());
			writer.print("\t");
			writer.write(employee.getEmployeeName());
			writer.print("\t\t");
			writer.write(employee.getEmployeeDesignation());
			writer.print("\t\t\t\t");
			writer.write(employee.getEmployeeDOB());
			writer.print("\t");
			writer.write(employee.getEmployeeSalary());
			writer.println();

			writer.flush();

		} catch (Exception e) {
			throw new Exception("Report Not able to generate");
		} finally {
			if (writer != null)
				writer.close();
		}

	}

	@Override
	public void generateEmployeesReport() throws Exception {
		List<Employee> listEmployees = employeeRepository.findAll();
		PrintWriter writer = null;
		try {
			File file = new File(util.getFileName());

			writer = new PrintWriter(file);
			writer.write(
					"Employee Id\t\t\t\t\tEmployee Name\t\t\t\tEmployee Desognation\tEmployee DOB\tEmployee Salary");
			writer.println();
			for (Employee employee : listEmployees) {
				writer.write(employee.getId());
				writer.print("\t");
				writer.write(employee.getEmployeeName());
				writer.print("\t\t");
				writer.write(employee.getEmployeeDesignation());
				writer.print("\t\t\t\t");
				writer.write(employee.getEmployeeDOB());
				writer.print("\t");
				writer.write(employee.getEmployeeSalary());
				writer.println();
			}
			writer.flush();

		} catch (Exception e) {
			throw new Exception("Report Not able to generate");
		} finally {
			if (writer != null)
				writer.close();
		}
	}

}
