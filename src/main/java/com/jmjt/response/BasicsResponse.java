package com.jmjt.response;

import java.util.List;

import com.jmjt.model.Basic;

public class BasicsResponse {

	List<Basic> basicDtos;

	public BasicsResponse(List<Basic> basicDtos) {
		super();
		this.basicDtos = basicDtos;
	}

	public List<Basic> getBasicDtos() {
		return basicDtos;
	}

	public void setBasicDtos(List<Basic> basicDtos) {
		this.basicDtos = basicDtos;
	}

}
