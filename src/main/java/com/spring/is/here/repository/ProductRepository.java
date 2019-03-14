package com.spring.is.here.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.Product;

/**
 * A repository interface for getting the entities
 * 
 * @author csabe812
 *
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

	/**
	 * Find all == SELECT * FROM PRODUCT
	 */
	List<Product> findAll();

	/**
	 * Find the cheapest one
	 * 
	 * @return
	 */
	Product findFirstByOrderByPrice();

	/**
	 * Find one by its name
	 * 
	 * @param name
	 * @return
	 */
	Product findByName(String name);
	
	List<Product> findByShopId(long id);
}
