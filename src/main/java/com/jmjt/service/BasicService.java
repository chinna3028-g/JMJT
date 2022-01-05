package com.jmjt.service;

import java.util.List;

import com.jmjt.model.Basic;

public interface BasicService 
{
	List<Basic> findAll();
	Basic findById(int id);
	Basic save(Basic theBasic);
	Basic deleteById(int id);
	Basic update(Basic theBasic);
}