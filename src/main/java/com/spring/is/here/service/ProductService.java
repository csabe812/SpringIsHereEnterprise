package com.spring.is.here.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.is.here.domain.Product;
import com.spring.is.here.domain.Shop;
import com.spring.is.here.repository.ProductRepository;
import com.spring.is.here.repository.ShopRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;
	private ShopRepository shopRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository, ShopRepository shopRepository) {
		this.productRepository = productRepository;
		this.shopRepository = shopRepository;
	}

	public List<Product> getProducts() {
		return this.productRepository.findAll();
	}
	
	public Product getProduct() {
		return this.productRepository.findFirstByOrderByPrice();
	}

	@PostConstruct
	public void init() {
		Shop shop = new Shop("InnerShop");
		shopRepository.save(shop);
		Product product = new Product("InnerProduct", 2000, "InnerDescription", shop);
		productRepository.save(product);
	}

	public Product getSpecificProduct(String name) {
		return this.productRepository.findByName(name);
	}
}
