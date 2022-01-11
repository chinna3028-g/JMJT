package com.jmjt.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jmjt.model.Employee;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>
{

}
