package com.jmjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmjt.dto.BasicDto;
import com.jmjt.dto.BasicsDto;
import com.jmjt.mapper.BasicMapper;
import com.jmjt.model.Basic;
import com.jmjt.request.CreateRequest;
import com.jmjt.service.BasicService;

@RestController
@RequestMapping("/basics")
public class BasicController 
{
	@Autowired
	private BasicService basicService;

	@Autowired
	private BasicMapper basicMapper;
	
	@GetMapping("")
	public BasicsDto findAll() {
		List<Basic> basics = basicService.findAll();
		List<BasicDto> basicDtos = basicMapper.map(basics);
		//Test Comments
		return new BasicsDto(basicDtos);
	}
	
	@GetMapping("/{id}")
	public BasicDto findById(@PathVariable int id) {
		Basic basic = basicService.findById(id);
		if(basic == null) {
			return null;
		}
		return basicMapper.map(basic);
	}
	
	@PostMapping("")
	public BasicDto save(@RequestBody CreateRequest createRequest) {
		Basic basic = basicMapper.map(createRequest);
		basic = basicService.save(basic);
		if(basic == null) {
			return null;
		}
		return basicMapper.map(basic);
	}
	
	@DeleteMapping("/{id}")
	public BasicDto deleteById(@PathVariable int id) {
		Basic basic = basicService.deleteById(id);
		if(basic == null) {
			return null;
		}
		return basicMapper.map(basic);
	}

	@PutMapping("")
	public BasicDto update(@RequestBody BasicDto basicDto) {
		Basic basic = basicMapper.map(basicDto);
		basic = basicService.update(basic);
		if(basic == null) {
			return null;
		}
		return basicMapper.map(basic);
	}
}
