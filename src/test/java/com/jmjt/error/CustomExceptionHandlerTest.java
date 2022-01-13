package com.jmjt.error;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CustomExceptionHandlerTest {

	@InjectMocks
	CustomExceptionHandler customExceptionHandler;

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void handleUserNotFoundExceptionTest() {

	}

}
