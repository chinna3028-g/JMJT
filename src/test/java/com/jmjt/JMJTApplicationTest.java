package com.jmjt;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.Silent.class)
public class JMJTApplicationTest {
	
	@InjectMocks
	private JMJTApplication app;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAll() throws Exception {
		RestTemplate template = app.getRestTemplate();
		assertNotNull(template);
	}

}
