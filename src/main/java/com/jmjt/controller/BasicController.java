package com.jmjt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/r")
public class BasicController 
{
	@Autowired
	private BasicService basicService;

	@Autowired
	private BasicMapper basicMapper;
	
	@GetMapping("")
	public ResponseEntity<BasicsDto> findAll() {
		List<Basic> basics = basicService.findAll();
		List<BasicDto> basicDtos = basicMapper.map(basics);
		return ResponseEntity.status(200).body( new BasicsDto(basicDtos));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BasicDto> findById(@PathVariable int id) {
		Basic basic = basicService.findById(id);
		if(basic == null) {
			return null;
		}
		return ResponseEntity.status(200).body( basicMapper.mapBasic(basic));
	}
	
	@PostMapping("")
	public ResponseEntity<BasicDto> save(@RequestBody CreateRequest createRequest) {
		Basic basic = basicMapper.mapCreateRequest(createRequest);
		basic = basicService.save(basic);
		if(basic == null) {
			return null;
		}
		 BasicDto basicDto = basicMapper.mapBasic(basic);
		 return ResponseEntity.status(200).body(basicDto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BasicDto> deleteById(@PathVariable int id) {
		Basic basic = basicService.deleteById(id);
		if(basic == null) {
			return null;
		}
		return ResponseEntity.status(200).body(basicMapper.mapBasic(basic));
		
	}

}
