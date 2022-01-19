package com.jmjt.service.impl.employee;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmjt.dao.EmployeeRepository;
import com.jmjt.error.InternalServerError;
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

	@Autowired
	private RestTemplate restTemplate;
	
	private static final String ERRPRMSG="Report Not able to generate";

	@Override
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
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
	public Employee findEmployeeByIdWithCurrency(String id) throws InternalServerError, RecordNotFoundException {
		Optional<Employee> employeeOptinal = employeeRepository.findById(id);

		if (!employeeOptinal.isPresent()) {
			throw new RecordNotFoundException("Resource Not Found");
		}
		Employee employee = employeeOptinal.get();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		String url = "https://mocki.io/v1/dd127c93-3db4-4164-b2cd-cc6d75e5e6d5";

		try {

			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

			

				Map<String, Object> map = extractRespopnseData(response.getBody());

				double salary = employee.getEmployeeSalary() != null ? Double.parseDouble(employee.getEmployeeSalary())
						: 0;
				double usd = map.get("USD") != "" ? Double.parseDouble((String) map.get("USD")) : 0.013516;

				employee.setEmployeeSalary("$" + salary * usd);
			
		} catch (Exception exception) {
			throw new InternalServerError("Failed To Execute");
		}

		return employee;
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> extractRespopnseData(String body)
			throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(body, Map.class);
	}

	@Override
	public Employee applySalaryIncrementById(String id) throws NotFoundException, RecordNotFoundException {
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

			} else if (sal >= 15000 && sal <= 25000) {
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
	public Employee saveEmployee(EmployeeCreateRequest employeeCreateRequest) throws InternalServerError {

		Employee employee = employeeRepository.save(mapper.mapEmployeeCreateRequest(employeeCreateRequest));
		if (employee.getId() == null) {
			throw new InternalServerError("Not Able to Save Data");
		}

		return employee;
	}

	@Override
	public Employee updateEmployee(EmployeeUpdateRequest updateEmployeeRequest) throws NotFoundException, RecordNotFoundException {
		findEmployeeById(updateEmployeeRequest.getEmployeeId());
		return employeeRepository.save(mapper.mapEmployeeUpdateRequest(updateEmployeeRequest));
	}

	@Override
	public void deleteEmployeeById(String id) throws NotFoundException, RecordNotFoundException {
		findEmployeeById(id);
		employeeRepository.deleteById(String.valueOf(id));
	}

	@Override
	public void generateEmployeeReportById(String employeeId) throws InternalServerError, RecordNotFoundException {
		Employee employee = findEmployeeById(employeeId);

		File file = new File(util.getFileName());
		try (PrintWriter writer = new PrintWriter(file)) {
			
			writer.write(
					"Employee Id\t\t\t\t\tEmployee Name\t\t\t\tEmployee Desognations\tEmployee DOB\tEmployee Salary");
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
			throw new InternalServerError(ERRPRMSG);
		} 

	}

	@Override
	public void generateEmployeesReport() throws InternalServerError {
		List<Employee> listEmployees = employeeRepository.findAll();
		File file = new File(util.getFileName());
		try (PrintWriter writer = new PrintWriter(file)) {

			writer.write(
					"Employee Id\t\t\t\t\tEmployee Name\t\t\t\tEmployee Desognation\tEmployee DOB\tEmployee Salary");
			writer.println();
			for (Employee emp : listEmployees) {
				writer.write(emp.getId());
				writer.print("\t");
				writer.write(emp.getEmployeeName());
				writer.print("\t\t");
				writer.write(emp.getEmployeeDesignation());
				writer.print("\t\t\t\t");
				writer.write(emp.getEmployeeDOB());
				writer.print("\t");
				writer.write(emp.getEmployeeSalary());
				writer.println();
			}
			writer.flush();

		} catch (Exception e) {
			throw new InternalServerError(ERRPRMSG);
		}
	}

}
