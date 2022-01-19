package com.jmjt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmjt.dao.BasicRepository;
import com.jmjt.error.InternalServerError;
import com.jmjt.error.NotFoundException;
import com.jmjt.mapper.Mapper;
import com.jmjt.model.Basic;
import com.jmjt.request.CreateRequest;
import com.jmjt.request.UpdateRequest;
import com.jmjt.service.BasicService;

@Service
public class BasicServiceImpl implements BasicService {
	private static final String ERROR_MSG = "No data found";

	@Autowired
	private BasicRepository basicRepository;

	@Autowired
	private Mapper basicMapper;

	@Override
	public List<Basic> findAll() {
		return basicRepository.findAll();
	}

	@Override
	public Basic findById(int id) throws NotFoundException {
		Optional<Basic> basic = basicRepository.findById(id);

		if (!basic.isPresent()) {
			throw new NotFoundException(ERROR_MSG);
		}
		return basic.get();
	}

	@Override
	public Basic save(CreateRequest createRequest) throws InternalServerError {

		Basic basic = basicRepository.save(basicMapper.mapCreateRequest(createRequest));

		if ( basic.getId() == 0) {
			throw new InternalServerError(ERROR_MSG);
		}
		return basic;
	}

	@Override
	public Basic deleteById(int id) throws NotFoundException {
		Basic basic = findById(id);
		basicRepository.deleteById(id);
		return basic;
	}

	@Override
	public Basic update(UpdateRequest updateRequest) throws NotFoundException {
		findById(updateRequest.getId());
		return basicRepository.save(basicMapper.mapUpdateRequest(updateRequest));
	}
}
