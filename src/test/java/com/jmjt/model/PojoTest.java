package com.jmjt.model;

import org.junit.Test;

import com.jmjt.dto.BasicDto;
import com.jmjt.dto.BasicsDto;
import com.jmjt.request.CreateRequest;
import com.jmjt.util.PojoTestUtils;

public class PojoTest {
	@Test
    public void testAccesorsShouldAccessProperField() {

        PojoTestUtils.validateAccessors(BasicDto.class);
        PojoTestUtils.validateAccessors(BasicsDto.class);
        PojoTestUtils.validateAccessors(Basic.class);
        PojoTestUtils.validateAccessors(CreateRequest.class);
    }
}
