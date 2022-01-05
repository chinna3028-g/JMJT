package com.jmjt.dto;

import java.util.List;

public class BasicsDto {

	List<BasicDto> basicDtos;

	public BasicsDto(List<BasicDto> basicDtos) {
		super();
		this.basicDtos = basicDtos;
	}

	public BasicsDto() {
		super();
	}

	public List<BasicDto> getBasicDtos() {
		return basicDtos;
	}

	public void setBasicDtos(List<BasicDto> basicDtos) {
		this.basicDtos = basicDtos;
	}

}
