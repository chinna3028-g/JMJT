package com.jmjt.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.error.RecordNotFoundException;
import com.jmjt.mapper.Mapper;
import com.jmjt.model.Employee;
import com.jmjt.service.employee.EmployeeService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmployeeManagementControllerTest {

	@InjectMocks
	private EmployeeManagementController controller;

	@Mock
	private EmployeeService service;

	@Mock
	private Mapper basicMapper;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	private String val1 = "{\r\n" + "    \"employeeName\" : \"Developer\",\r\n"
			+ "    \"employeeDOB\":\"10/11/2000\",\r\n" + "    \"employeeDesignation\":\"Software Developer\",\r\n"
			+ "    \"employeeSalary\":\"100000\"\r\n" + "}";

	private String val2 = "{\r\n" + "    \"employeeName\" : \"Developer\",\r\n"
			+ "    \"employeeDOB\":\"10/11/2000\",\r\n" + "    \"employeeDesignation\":\"Software Developer\",\r\n"
			+ "    \"employeeSalary\":\"100000\",\r\n" + "    \"employeeId\":\"61dc09368fa7333c4c20e88f\"\r\n" + "}";

	@Test
	public void testFindAll() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void findEmployeeByIdTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(service.findEmployeeById(ArgumentMatchers.anyString())).thenReturn(new Employee());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void findEmployeeByIdExceptionTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(service.findEmployeeById(ArgumentMatchers.anyString())).thenThrow(RecordNotFoundException.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

	@Test
	public void findEmployeeByIdNullTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(service.findEmployeeById(ArgumentMatchers.anyString())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void findEmployeeByIdWithCurrencyTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/61dc09368fa7333c4c20e88f/usd")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(service.findEmployeeByIdWithCurrency(ArgumentMatchers.anyString())).thenReturn(new Employee());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test(expected = Exception.class)
	public void findEmployeeByIdWithCurrencyExceptionTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/61dc09368fa7333c4c20e88f/usd")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(service.findEmployeeByIdWithCurrency(ArgumentMatchers.anyString())).thenThrow(Exception.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

	@Test
	public void saveEmployeeTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(val1);
		Mockito.when(service.saveEmployee(ArgumentMatchers.any())).thenReturn(new Employee());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}

	@Test
	public void saveEmployeeExceptionTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/employee").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(val1);
		Mockito.when(service.saveEmployee(ArgumentMatchers.any())).thenThrow(InternalServerError.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.EXPECTATION_FAILED.value(), response.getStatus());
	}

	@Test
	public void updateEmployeeTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(val2);
		Mockito.when(service.updateEmployee(ArgumentMatchers.any())).thenReturn(new Employee());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void updateEmployeeExceptionTest() throws Exception  {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON).content(val2);
		Mockito.when(service.updateEmployee(ArgumentMatchers.any())).thenThrow(NotFoundException.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.EXPECTATION_FAILED.value(), response.getStatus());
	}

	@Test
	public void applySalaryIncrementToAllTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/hike").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void applySalaryIncrementByIdTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/hike/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void applySalaryIncrementByIdExceptionTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/hike/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.when(service.applySalaryIncrementById(ArgumentMatchers.any())).thenThrow(NotFoundException.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

	@Test
	public void deleteEmployeeByIdTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void deleteByIdExceptionTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/employee/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON).header("api-version", 1);
		Mockito.doThrow(NotFoundException.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.NO_CONTENT.value(), response.getStatus());
	}

	@Test
	public void generateEmployeesReportTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/report")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void generateEmployeeReportByIdTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/report/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void generateEmployeeReportByIdExceptionTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/employee/report/61dc09368fa7333c4c20e88f")
				.accept(MediaType.APPLICATION_JSON);
		Mockito.doThrow(Exception.class);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
	}

}
