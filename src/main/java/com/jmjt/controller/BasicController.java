package com.jmjt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.model.Basic;
import com.jmjt.request.CreateRequest;
import com.jmjt.request.UpdateRequest;
import com.jmjt.response.BasicsResponse;
import com.jmjt.service.BasicService;

@RestController
@RequestMapping("/r")
public class BasicController {
	@Autowired
	private BasicService basicService;

	@GetMapping("")
	public ResponseEntity<BasicsResponse> findAll() {
		return ResponseEntity.status(200).body(new BasicsResponse(basicService.findAll()));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Basic> findById(@PathVariable int id) throws NotFoundException {
		return ResponseEntity.status(200).body(basicService.findById(id));
	}

	@PostMapping("")
	public ResponseEntity<Basic> save(@RequestBody CreateRequest createRequest) throws InternalServerError {
		return ResponseEntity.status(200).body(basicService.save(createRequest));
	}

	@PutMapping("")
	public ResponseEntity<Basic> update(@RequestBody UpdateRequest updateRequest) throws NotFoundException {
		return ResponseEntity.status(200).body(basicService.update(updateRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Basic> deleteById(@PathVariable int id) throws NotFoundException {
		return ResponseEntity.status(200).body(basicService.deleteById(id));
	}

}
