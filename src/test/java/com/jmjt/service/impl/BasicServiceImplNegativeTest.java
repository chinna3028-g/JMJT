package com.jmjt.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
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

import com.jmjt.dao.BasicRepository;
import com.jmjt.error.NotFoundException;
import com.jmjt.model.Basic;
import com.jmjt.request.UpdateRequest;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BasicServiceImplNegativeTest {
	@InjectMocks
	private BasicServiceImpl basicServiceImpl;
	@Mock
	private BasicRepository basicRepository;

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllNulltest() {
		List<Basic> list = null;
		Mockito.when(basicRepository.findAll()).thenReturn(list);
		List<Basic> mockList = basicServiceImpl.findAll();
		assertNull(mockList);
	}

	@Test
	public void findAllAnyOneObjectNulltest() {
		List<Basic> list = new ArrayList<Basic>();
		Basic basic1 = null;
		list.add(basic1);
		Basic basic2 = new Basic();
		basic2.setId(10);
		basic2.setName("Chinna");
		list.add(basic2);
		Mockito.when(basicRepository.findAll()).thenReturn(list);
		List<Basic> mockList = basicServiceImpl.findAll();
		assertNotNull(mockList);
		assertNull(mockList.get(0));
		assertNotNull(mockList.get(1));
	}

	@Test
	public void findByIdNameNotNullTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setId(1);
		basic.setName("Chinna");
		Optional<Basic> basicOptional = Optional.of(basic);
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(basicOptional);
		Basic mockBasic = basicServiceImpl.findById(1);
		assertNotNull(mockBasic.getName());
	}

	@Test(expected = NotFoundException.class)
	public void deleteExceptionTest() throws NotFoundException {
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		basicServiceImpl.deleteById(1);
	}

	@Test(expected = NotFoundException.class)
	public void updateExceptionTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setName("Chinna");
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());
		Mockito.when(basicRepository.save(ArgumentMatchers.any())).thenReturn(basic);
		UpdateRequest ur = new UpdateRequest();
		ur.setName("NAME");
		ur.setId(1);
		basicServiceImpl.update(ur);
	}
}
