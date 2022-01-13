package com.jmjt.mapper;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringRunner;

import com.jmjt.model.Basic;
import com.jmjt.model.Employee;
import com.jmjt.request.CreateRequest;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;
import com.jmjt.request.UpdateRequest;

@RunWith(SpringRunner.class)
public class MapperTest {

	private static final String NAME = "NAME";
	private static final String SALARY = "15000";
	private static final String DESIGNATION = "SOFTWARE DEVELOPER";
	private static final String DOB = "08/11/1990";
	private static final String ID = "61dc04a78fa73329e8842caa";

	@InjectMocks
	private Mapper basicMapper;

	@Test
	public void should_map_createRequest_to_Basic() {
		// Given
		CreateRequest createRequest = givenCreateRequest();
		// When
		Basic basic = basicMapper.mapCreateRequest(createRequest);
		// Then
		assertEquals(basic.getName(), createRequest.getName());
	}

	@Test
	public void should_map_updateRequest_to_Basic() {
		// Given
		UpdateRequest updateRequest = givenUpdateRequest();
		// When
		Basic basic = basicMapper.mapUpdateRequest(updateRequest);
		// Then
		assertEquals(basic.getName(), updateRequest.getName());
	}

	@Test
	public void should_map_employeeCreateRequest_to_Employee() {
		// Given
		EmployeeCreateRequest employeeCreateRequest = givenEmployeeCreateRequest();
		// When
		Employee employee = basicMapper.mapEmployeeCreateRequest(employeeCreateRequest);
		// Then
		assertEquals(employee.getEmployeeName(), employeeCreateRequest.getEmployeeName());
	}

	@Test
	public void should_map_employeeUpdateRequest_to_Employee() {
		// Given
		EmployeeUpdateRequest employeeUpdateRequest = givenEmployeeUpdateRequest();
		// When
		Employee employee = basicMapper.mapEmployeeUpdateRequest(employeeUpdateRequest);
		// Then
		assertEquals(employee.getEmployeeName(), employeeUpdateRequest.getEmployeeName());
	}

	private CreateRequest givenCreateRequest() {
		CreateRequest createRequest = new CreateRequest();
		createRequest.setName(NAME);
		return createRequest;
	}

	private UpdateRequest givenUpdateRequest() {
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.setName(NAME);
		return updateRequest;
	}

	private EmployeeCreateRequest givenEmployeeCreateRequest() {
		EmployeeCreateRequest request = new EmployeeCreateRequest();
		request.setEmployeeName(NAME);
		request.setEmployeeSalary(SALARY);
		request.setEmployeeDOB(DOB);
		request.setEmployeeDesignation(DESIGNATION);
		return request;
	}

	private EmployeeUpdateRequest givenEmployeeUpdateRequest() {
		EmployeeUpdateRequest request = new EmployeeUpdateRequest();
		request.setEmployeeName(NAME);
		request.setEmployeeSalary(SALARY);
		request.setEmployeeDOB(DOB);
		request.setEmployeeDesignation(DESIGNATION);
		request.setEmployeeId(ID);
		return request;
	}
}
