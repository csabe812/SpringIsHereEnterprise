package com.spring.is.here.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.County;
import com.spring.is.here.domain.User;

public interface StateRepository extends CrudRepository<County, Long> {

	County findById(long id);
	
	List<County> findAll();
	
}