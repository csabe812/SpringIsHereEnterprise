package com.spring.is.here.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findById(long id);
	
	User findByEmail(String email);

	User findByActivation(String code);
	
	List<User> findAll();
	
}
