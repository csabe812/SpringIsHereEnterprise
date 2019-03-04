/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.is.here.domain.Product;

/**
 * This is the HomeController class a.k.a. the main page a.k.a index
 * 
 * @author csabe812
 *
 */
@Controller
public class HomeController {

	/**
	 * Adds the products to the model
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String products(Model model) {
		model.addAttribute("products", getProducts());
		return "products";
	}

	/**
	 * Searches for a product. If the id is null, an exception will be thrown.
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/product/{id}")
	public String searchForProduct(@PathVariable(value = "id") String id) throws Exception {
		if (id == null || id.equals("-1")) {
			throw new Exception("Given id was not found or not exists");
		}
		return "product";
	}

	/**
	 * Return two dummy products.
	 * 
	 * @return
	 */
	public ArrayList<Product> getProducts() {
		ArrayList<Product> products = new ArrayList<Product>();

		Product p1 = new Product("Story", 100, "Magazine");
		Product p2 = new Product("Penna", 10, "Pen");

		products.add(p1);
		products.add(p2);

		return products;
	}

}
