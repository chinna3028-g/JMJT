package com.jmjt.util;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class JMJTUtil {
	
	public String getFileName() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(Date.from(Instant.now()));
		String fileName = String.format("employeesReport-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.txt", cal);
		return "reports\\" + fileName;
	}
	
}
