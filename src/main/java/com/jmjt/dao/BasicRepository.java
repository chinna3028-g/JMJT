package com.jmjt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jmjt.model.Basic;

@Repository
public interface BasicRepository extends JpaRepository<Basic, Integer>
{

}
