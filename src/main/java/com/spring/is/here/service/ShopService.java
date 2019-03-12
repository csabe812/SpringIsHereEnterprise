package com.spring.is.here.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.is.here.domain.Shop;
import com.spring.is.here.repository.ShopRepository;

/**
 * Service class for getting the info from the repos.
 * 
 * @author csabe812
 *
 */
@Service
public class ShopService {

	private ShopRepository shopRepository;

	/**
	 * Constructor using fields
	 * 
	 * @param shopRepository
	 */
	@Autowired
	public ShopService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	/**
	 * Getter for the shops
	 * 
	 * @return
	 */
	public List<Shop> getShops() {
		return this.shopRepository.findAll();
	}
}
