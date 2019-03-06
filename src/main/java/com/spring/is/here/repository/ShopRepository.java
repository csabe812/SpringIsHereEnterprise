package com.spring.is.here.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.Shop;

/**
 * A ShopRepo interface for getting the entities
 * 
 * @author csabe812
 *
 */
public interface ShopRepository extends CrudRepository<Shop, Long> {

	/**
	 * Get all == SELECT * FROM SHOP;
	 */
	List<Shop> findAll();

}
