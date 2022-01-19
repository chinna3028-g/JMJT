package com.jmjt.service;

import java.util.List;

import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.model.Basic;
import com.jmjt.request.CreateRequest;
import com.jmjt.request.UpdateRequest;

public interface BasicService {

	List<Basic> findAll();

	Basic findById(int id) throws NotFoundException;

	Basic save(CreateRequest createRequest) throws InternalServerError;

	Basic deleteById(int id) throws NotFoundException;

	Basic update(UpdateRequest updateRequest) throws NotFoundException;
}