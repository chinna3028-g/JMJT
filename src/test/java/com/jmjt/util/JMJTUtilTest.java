package com.jmjt.util;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

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

	@Before
	public void init() throws NoSuchAlgorithmException, InvalidKeySpecException {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFileNameTest() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));
		String fileName = "reports\\" + String.format("employeesReport-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal);
		assertEquals(fileName, util.getFileName());
	}

}
