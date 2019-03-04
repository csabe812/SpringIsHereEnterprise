package com.spring.is.here.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.is.here.domain.Product;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String products(Model model) {
		model.addAttribute("products", getProducts());
		return "products";
	}
	
	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();
		
		Product p1 = new Product("Story", 100, "Magazine");
		Product p2 = new Product("Penna", 10, "Pen");
		
		products.add(p1);
		products.add(p2);
		
		return products;
	}
	
}
