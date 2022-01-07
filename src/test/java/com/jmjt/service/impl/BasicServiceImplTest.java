package com.jmjt.service.impl;

import static org.junit.Assert.assertEquals;

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


@RunWith(MockitoJUnitRunner.Silent.class)
public class BasicServiceImplTest {
	@InjectMocks
	private BasicServiceImpl basicServiceImpl;
	@Mock
	private BasicRepository basicRepository;

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {
		List<Basic> list = new ArrayList<Basic>();
		list.add(new Basic());
		Mockito.when(basicRepository.findAll()).thenReturn(list);
		List<Basic> mockList = basicServiceImpl.findAll();
		assertEquals(1, mockList.size());
	}

	@Test
	public void findByIdTest() throws NotFoundException {
		Optional<Basic> basic = Optional.of(new Basic());
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(basic);
		 basicServiceImpl.findById(1);
	}

	@Test(expected=NotFoundException.class)
	public void findByIdNullTest() throws NotFoundException {
		Optional<Basic> basic = Optional.empty();
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(basic);
		 basicServiceImpl.findById(1);

	}

	@Test(expected=NotFoundException.class)
	public void saveNullTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setId(0);
		 basicServiceImpl.save(basic);
	}

	@Test
	public void saveTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setId(1);
		Mockito.when(basicRepository.save(ArgumentMatchers.any())).thenReturn(basic);
		Basic mockBasic = basicServiceImpl.save(basic);
		assertEquals(1, mockBasic.getId());
	}

	@Test
	public void deleteTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setId(1);
		Optional<Basic> basicOpt = Optional.of(basic);
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(basicOpt);

		basic = basicServiceImpl.deleteById(1);
		assertEquals(1, basic.getId());
	}

	@Test
	public void UpdateTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setId(1);
		Optional<Basic> basicOpt = Optional.of(basic);
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(basicOpt);

		Mockito.when(basicRepository.save(ArgumentMatchers.any())).thenReturn(basic);
		Basic mockBasic = basicServiceImpl.update(basic);
		assertEquals(1, mockBasic.getId());
	}

	@Test(expected=NotFoundException.class)
	public void updateNullTest() throws NotFoundException {
		Basic basic = new Basic();
		basic.setId(1);
		Optional<Basic> basicOpt = Optional.empty();
		Mockito.when(basicRepository.findById(ArgumentMatchers.anyInt())).thenReturn(basicOpt);

		Mockito.when(basicRepository.save(ArgumentMatchers.any())).thenReturn(basic);
		 basicServiceImpl.update(basic);
	}
}
