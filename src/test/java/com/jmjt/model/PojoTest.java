package com.jmjt.model;

import org.junit.Test;

import com.jmjt.request.CreateRequest;
import com.jmjt.request.EmployeeCreateRequest;
import com.jmjt.request.EmployeeUpdateRequest;
import com.jmjt.response.BasicsResponse;
import com.jmjt.response.employee.EmployeeCreateResponse;
import com.jmjt.response.employee.EmployeeListResponse;
import com.jmjt.response.employee.EmployeeUpdateResponse;
import com.jmjt.util.PojoTestUtils;

public class PojoTest {
	@Test
	public void testAccesorsShouldAccessProperField() {

		PojoTestUtils.validateAccessors(Basic.class);
		PojoTestUtils.validateAccessors(Employee.class);
		PojoTestUtils.validateAccessors(EmployeeCreateRequest.class);
		PojoTestUtils.validateAccessors(EmployeeCreateResponse.class);
		PojoTestUtils.validateAccessors(EmployeeUpdateRequest.class);
		PojoTestUtils.validateAccessors(EmployeeUpdateResponse.class);
		PojoTestUtils.validateAccessors(EmployeeListResponse.class);
		PojoTestUtils.validateAccessors(CreateRequest.class);
		PojoTestUtils.validateAccessors(BasicsResponse.class);
	}
}
