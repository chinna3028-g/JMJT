package com.jmjt.util;

import org.springframework.stereotype.Component;

@Component
public class JMJTUtil {

	public String getFileName(String employeeId) {
		return "reports\\" + "employeeReport-" + employeeId + ".txt";
	}

	public String getFileName(int length) {
		return "reports\\" + "employeeReport-" + length + ".txt";
	}

}
