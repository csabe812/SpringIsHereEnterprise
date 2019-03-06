package com.spring.is.here.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findAll();
	
	Product findFirstByOrderByPrice();

	Product findByName(String name);
}
