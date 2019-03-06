package com.spring.is.here.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.spring.is.here.domain.Product;
import com.spring.is.here.domain.Shop;

public interface ShopRepository extends CrudRepository<Shop, Long> {

	List<Shop> findAll();
	
}
