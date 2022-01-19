package com.jmjt.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.jmjt.dao.EmployeeRepository;
import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.error.RecordNotFoundException;
import com.jmjt.mapper.Mapper;
import com.jmjt.model.Employee;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;
import com.jmjt.service.impl.employee.EmployeeServiceImpl;
import com.jmjt.util.JMJTUtil;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl service;

	@Mock
	private EmployeeRepository repository;

	@Mock
	private Mapper mapper;

	@Mock
	private JMJTUtil util;

	@Mock
	private RestTemplate restTemplate;

	private String DUMMY_ID = "61dc04a78fa73329e8842caa";

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void fetchAllEmployeesTest() {
		List<Employee> list = new ArrayList<Employee>();
		list.add(new Employee());
		Mockito.when(repository.findAll()).thenReturn(list);
		List<Employee> mockList = service.fetchAllEmployees();
		assertEquals(1, mockList.size());
	}

	@Test
	public void findEmployeeByIdTest() throws NotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(new Employee()));
		service.findEmployeeById(DUMMY_ID);
	}

	@Test
	public void findEmployeeByIdWithCurrencyTest() throws Exception {
		Mockito.when(repository.findById(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "14700")));

		ResponseEntity<String> entity = new ResponseEntity<String>("{\"USD\":\"0.013516\"}", HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(entity);

		service.findEmployeeByIdWithCurrency(DUMMY_ID);
	}

	@Test(expected = RecordNotFoundException.class)
	public void findEmployeeByIdWithCurrencyExceptionTest1() throws Exception {

		Mockito.when(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
		ResponseEntity<String> entity = new ResponseEntity<String>("{\"USD\":\"0.013516\"}", HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(entity);

		service.findEmployeeByIdWithCurrency(DUMMY_ID);

	}

	@Test
	public void findEmployeeByIdWithCurrencyExceptionTest2() throws Exception {

		Mockito.when(repository.findById(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, null)));
		ResponseEntity<String> entity = new ResponseEntity<String>("{\"USD\":\"0.013516\"}", HttpStatus.ACCEPTED);

		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(entity);

		service.findEmployeeByIdWithCurrency(DUMMY_ID);

	}

	@Test
	public void findEmployeeByIdWithCurrencyExceptionTest3() throws Exception {

		Mockito.when(repository.findById(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "15000")));
		ResponseEntity<String> entity = new ResponseEntity<String>("{\"USD\":\"\"}", HttpStatus.ACCEPTED);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(entity);

		service.findEmployeeByIdWithCurrency(DUMMY_ID);

	}

	@Test
	public void findEmployeeByIdWithCurrencyExceptionTest4() throws InternalServerError, RecordNotFoundException {

		Mockito.when(repository.findById(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "15000")));
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(ResponseEntity.ok("{\"name\":\"deepak\", \"USD\":\"21\"}"));

		service.findEmployeeByIdWithCurrency(DUMMY_ID);

	}

	@Test
	public void extractRespopnseDataTest() throws Exception {

		Map<String, Object> resultMap = service.extractRespopnseData("{\"USD\":\"0.013516\"}");

		assertNotNull(resultMap);
		assertNotNull(resultMap.get("USD"));
		assertEquals("0.013516", resultMap.get("USD"));

	}

	@Test(expected = Exception.class)
	public void findEmployeeByIdWithCurrencyExceptionTest5() throws Exception {

		Mockito.when(repository.findById(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, null)));
		ResponseEntity<String> entity = new ResponseEntity<String>("", HttpStatus.ACCEPTED);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
				ArgumentMatchers.any(), ArgumentMatchers.<Class<String>>any())).thenReturn(entity);

		service.findEmployeeByIdWithCurrency(DUMMY_ID);

	}

	@Test(expected = RecordNotFoundException.class)
	public void findEmployeeByIdExceptionTest() throws RecordNotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
		service.findEmployeeById(DUMMY_ID);
	}

	@Test
	public void applySalaryIncrementByIdTest1() throws NotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.any()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "14000")));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "14700"));

		Employee emp = service.applySalaryIncrementById(DUMMY_ID);
		assertEquals("14700", emp.getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementByIdTest3() throws NotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.any()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "15000")));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "15750"));

		Employee emp = service.applySalaryIncrementById(DUMMY_ID);
		assertEquals("15750", emp.getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementByIdTest6() throws NotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.any()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "16000")));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "16400"));

		Employee emp = service.applySalaryIncrementById(DUMMY_ID);
		assertEquals("16400", emp.getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementByIdTest4() throws NotFoundException {
		Mockito.when(repository.findById(DUMMY_ID)).thenReturn(Optional.of(getEmployee(DUMMY_ID, "20000")));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "20800"));

		Employee emp = service.applySalaryIncrementById(DUMMY_ID);
		assertEquals("20800", emp.getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementByIdTest2() throws NotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.any()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "30000")));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "30900"));

		Employee emp = service.applySalaryIncrementById(DUMMY_ID);
		assertEquals("30900", emp.getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementByIdTest5() throws NotFoundException {
		Mockito.when(repository.findById(ArgumentMatchers.any())).thenReturn(Optional.of(getEmployee(DUMMY_ID, null)));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "0"));
		Employee emp = service.applySalaryIncrementById(DUMMY_ID);
		assertEquals("0", emp.getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementToAllTest1() throws NotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "14000"));
		Mockito.when(repository.findAll()).thenReturn(list);
		List<Employee> listEmp = service.applySalaryIncrementToAll();
		assertEquals("14700", listEmp.get(0).getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementToAllTest2() throws NotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "15000"));
		Mockito.when(repository.findAll()).thenReturn(list);
		List<Employee> listEmp = service.applySalaryIncrementToAll();
		assertEquals("15600", listEmp.get(0).getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementToAllTest5() throws NotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "16000"));
		Mockito.when(repository.findAll()).thenReturn(list);
		List<Employee> listEmp = service.applySalaryIncrementToAll();
		assertEquals("16640", listEmp.get(0).getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementToAllTest6() throws NotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "24999"));
		Mockito.when(repository.findAll()).thenReturn(list);
		List<Employee> listEmp = service.applySalaryIncrementToAll();
		assertEquals("25998", listEmp.get(0).getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementToAllTest3() throws NotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "30000"));

		Mockito.when(repository.findAll()).thenReturn(list);

		List<Employee> listEmp = service.applySalaryIncrementToAll();
		assertEquals("30900", listEmp.get(0).getEmployeeSalary());
	}

	@Test
	public void applySalaryIncrementToAllTest4() throws NotFoundException {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, null));

		Mockito.when(repository.findAll()).thenReturn(list);

		List<Employee> listEmp = service.applySalaryIncrementToAll();
		assertEquals("0", listEmp.get(0).getEmployeeSalary());
	}

	@Test
	public void saveEmployeeTest() throws Exception {
		EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest();
		employeeCreateRequest.setEmployeeSalary("15000");
		Mockito.when(mapper.mapEmployeeCreateRequest(ArgumentMatchers.any()))
				.thenReturn(getEmployee(DUMMY_ID, "15000"));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "15000"));
		Employee emp = service.saveEmployee(employeeCreateRequest);
		assertEquals(DUMMY_ID, emp.getId());
	}

	@Test(expected = Exception.class)
	public void saveEmployeeExceptionTest() throws Exception {
		EmployeeCreateRequest employeeCreateRequest = new EmployeeCreateRequest();
		employeeCreateRequest.setEmployeeSalary("15000");
		Mockito.when(mapper.mapEmployeeCreateRequest(ArgumentMatchers.any()))
				.thenReturn(getEmployee(DUMMY_ID, "15000"));
		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(null, "15000"));
		service.saveEmployee(employeeCreateRequest);
	}

	@Test
	public void deleteEmployeeByIdTest() throws NotFoundException {
		Employee employee = new Employee();
		employee.setId(DUMMY_ID);
		Optional<Employee> employeeOpt = Optional.of(employee);
		Mockito.when(repository.findById(ArgumentMatchers.anyString())).thenReturn(employeeOpt);
		service.deleteEmployeeById(DUMMY_ID);
	}

	@Test
	public void updateEmployeeTest() throws NotFoundException {
		EmployeeUpdateRequest ur = new EmployeeUpdateRequest();
		ur.setEmployeeId(DUMMY_ID);
		Mockito.when(mapper.mapEmployeeUpdateRequest(ArgumentMatchers.any()))
				.thenReturn(getEmployee(DUMMY_ID, "10000"));
		Mockito.when(repository.findById(ArgumentMatchers.anyString()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "10000")));

		Mockito.when(repository.save(ArgumentMatchers.any())).thenReturn(getEmployee(DUMMY_ID, "10000"));
		Employee mockEmployee = service.updateEmployee(ur);
		assertEquals(DUMMY_ID, mockEmployee.getId());
	}

	@Test
	public void generateEmployeeReportByIdTest1() throws Exception {
		Mockito.when(repository.findById(ArgumentMatchers.any()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "14000")));
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));
		Mockito.when(util.getFileName()).thenReturn(
				"reports\\" + String.format("employeesReport-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal));

		service.generateEmployeeReportById(DUMMY_ID);
	}

	@Test(expected = Exception.class)
	public void generateEmployeeReportByIdTest2() throws Exception {
		Mockito.when(repository.findById(ArgumentMatchers.any()))
				.thenReturn(Optional.of(getEmployee(DUMMY_ID, "14000")));

		Mockito.when(util.getFileName()).thenReturn(null);
		service.generateEmployeeReportById(DUMMY_ID);
	}

	@Test
	public void generateEmployeesReportTest1() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "15000"));
		Mockito.when(repository.findAll()).thenReturn(list);
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));
		Mockito.when(util.getFileName()).thenReturn(
				"reports\\" + String.format("employeesReport-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal));

		service.generateEmployeesReport();
	}

	@Test(expected = Exception.class)
	public void generateEmployeesReportTest2() throws Exception {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee(DUMMY_ID, "15000"));
		Mockito.when(repository.findAll()).thenReturn(list);
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));
		Mockito.when(util.getFileName()).thenReturn(null);

		service.generateEmployeesReport();
	}

	public Employee getEmployee(String id, String salary) {
		Employee employee = new Employee();
		employee.setId(id);
		employee.setEmployeeSalary(salary);
		employee.setEmployeeName("DUMMY");
		employee.setEmployeeDesignation("DUMMY");
		employee.setEmployeeDOB("DUMMY");
		return employee;
	}

}
