package com.spring.is.here.repository;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByRole(String role);
	
}
