package com.spring.is.here.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);
	
}
