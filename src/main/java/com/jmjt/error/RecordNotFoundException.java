package com.jmjt.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException(String message) {
		super(message);
	}
}