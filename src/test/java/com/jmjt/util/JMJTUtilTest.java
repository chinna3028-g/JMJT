package com.jmjt.util;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JMJTUtilTest {

	@InjectMocks
	JMJTUtil util;

	private String DUMMY_ID = "61dc04a78fa73329e8842caa";

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFileNameWithEmployeeIDTest() {
		assertEquals("reports\\" + "employeeReport-" + DUMMY_ID + ".txt", util.getFileName(DUMMY_ID));
	}

	@Test
	public void getFileNameWithLengthTest() {
		assertEquals("reports\\" + "employeeReport-1.txt", util.getFileName(1));
	}

}
