package com.spring.is.here.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.is.here.domain.Product;
import com.spring.is.here.service.ProductService;

@RestController
public class ApiController {

	private ProductService productService;

	/**
	 * Constructor
	 * 
	 * @param productService
	 */
	@Autowired
	public ApiController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/productname/{name}")
	public Product searchForProductByName(@PathVariable(value = "name") String name) throws Exception {
		return this.productService.getProduct();
	}
	
}
