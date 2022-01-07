package com.jmjt.service;

import java.util.List;

import com.jmjt.error.NotFoundException;
import com.jmjt.model.Basic;


public interface BasicService 
{
	List<Basic> findAll();
	Basic findById(int id) throws NotFoundException;
	Basic save(Basic theBasic) throws NotFoundException;
	Basic deleteById(int id) throws NotFoundException;
	Basic update(Basic theBasic) throws NotFoundException;
}