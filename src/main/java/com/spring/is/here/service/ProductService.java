package com.spring.is.here.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.is.here.domain.Product;
import com.spring.is.here.domain.Shop;
import com.spring.is.here.repository.ProductRepository;
import com.spring.is.here.repository.ShopRepository;

/**
 * Service class for getting the info from the repos.
 * 
 * @author csabe812
 *
 */
@Service
public class ProductService {

	private ProductRepository productRepository;
	private ShopRepository shopRepository;

	/**
	 * Constructor using fields
	 * 
	 * @param productRepository
	 * @param shopRepository
	 */
	@Autowired
	public ProductService(ProductRepository productRepository, ShopRepository shopRepository) {
		this.productRepository = productRepository;
		this.shopRepository = shopRepository;
	}

	/**
	 * Getter for the products
	 * 
	 * @return
	 */
	public List<Product> getProducts() {
		return this.productRepository.findAll();
	}

	/**
	 * Getter for one product
	 * 
	 * @return
	 */
	public Product getProduct() {
		return this.productRepository.findFirstByOrderByPrice();
	}

	/**
	 * Initializing with two products and storing it in a shop
	 */
	/*@PostConstruct
	public void init() {
		Shop shop = new Shop("InnerShop");
		shopRepository.save(shop);
		Product product1 = new Product("InnerProduct1", 1111, "InnerDescription1", shop);
		productRepository.save(product1);
		Product product2 = new Product("InnerProduct2", 2222, "InnerDescription2", shop);
		productRepository.save(product2);
	}*

	/**
	 * Getting a {name} product
	 * 
	 * @param name
	 * @return
	 */
	public Product getSpecificProduct(String name) {
		return this.productRepository.findByName(name);
	}
}
