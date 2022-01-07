package com.jmjt.initializer;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.jmjt.service.BasicService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BasicInitializerTest {

	@InjectMocks
	private BasicInitializer basicInitializer;
	@Mock
	private BasicService basicService;

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testRun() throws Exception {
		basicInitializer.run("hello");
	}
}
