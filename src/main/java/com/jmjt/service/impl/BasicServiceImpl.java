package com.jmjt.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jmjt.dao.BasicRepository;
import com.jmjt.error.NotFoundException;
import com.jmjt.model.Basic;
import com.jmjt.service.BasicService;


@Service
public class BasicServiceImpl implements BasicService {
	private final String ERROR_MSG="No data found"; 
	@Autowired
	private BasicRepository basicRepository;

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
	public Basic save(Basic basic) throws NotFoundException {
		if (basic.getId() == 0) {
			throw new NotFoundException(ERROR_MSG);
		}
		return basicRepository.save(basic);
	}

	@Override
	public Basic deleteById(int id) throws NotFoundException {
		Basic basic = findById(id);
		basicRepository.deleteById(id);
		return basic;
	}

	@Override
	public Basic update(Basic basic) throws NotFoundException {
		basic = findById(basic.getId());
		return basicRepository.save(basic);
	}
}
