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

import com.jmjt.mapper.Mapper;
import com.jmjt.model.Basic;
import com.jmjt.service.BasicService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BasicControllerTest {
	@InjectMocks
	private BasicController basicControl;
	@Mock
	private BasicService basicService;

	@Mock
	private Mapper basicMapper;

	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(basicControl).build();
	}

	private String val1 = "{\r\n" + "\"name\":\"deepak\"\r\n" + "}";

	@Test
	public void testSaveNull() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/r").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(val1);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testSave() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/r").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(val1);
		Mockito.when(basicService.save(ArgumentMatchers.any())).thenReturn(new Basic());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void updateTest() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/r").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(val1);
		Mockito.when(basicService.update(ArgumentMatchers.any())).thenReturn(new Basic());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testFindAll() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/r").accept(MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void findByIdTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/r/1").accept(MediaType.APPLICATION_JSON);
		Mockito.when(basicService.findById(ArgumentMatchers.anyInt())).thenReturn(new Basic());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void findByIdNullTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/r/1").accept(MediaType.APPLICATION_JSON);
		Mockito.when(basicService.findById(ArgumentMatchers.anyInt())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void deleteByIdTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/r/1").accept(MediaType.APPLICATION_JSON);
		Mockito.when(basicService.deleteById(ArgumentMatchers.anyInt())).thenReturn(new Basic());
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void deleteByIdNullTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/r/1").accept(MediaType.APPLICATION_JSON)
				.header("api-version", 1);
		Mockito.when(basicService.deleteById(ArgumentMatchers.anyInt())).thenReturn(null);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

}
