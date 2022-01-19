package com.jmjt.model;

import java.io.Serializable;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import com.jmjt.request.EmployeeCreateRequest;

@Document(collection = "employee")
public class Employee extends EmployeeCreateRequest implements Serializable {

	private static final long serialVersionUID = 5884277966749458969L;

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	

}
