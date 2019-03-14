/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.is.here.service.ProductService;
import com.spring.is.here.service.ShopService;

/**
 * This is the HomeController class a.k.a. the main page a.k.a index
 * 
 * @author csabe812
 *
 */
@Controller
public class HomeController {

	private ProductService productService;
	private ShopService shopService;
	
	/**
	 * Constructor
	 * 
	 * @param productService
	 */
	@Autowired
	public HomeController(ProductService productService, ShopService shopService) {
		this.productService = productService;
		this.shopService = shopService;
	}

	/**
	 * Adds the products to the model
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/products")
	public String products(Model model) {
		model.addAttribute("products", this.productService.getProducts());
		return "products";
	}

	@RequestMapping("/")
	public String shops(Model model) {
		model.addAttribute("shops", this.shopService.getShops());
		return "shops";
	}
	
	
	/**
	 * Get only ONE product
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/product")
	public String product(Model model) {
		model.addAttribute("product", this.productService.getProduct());
		return "product";
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
	 * 
	 * Get a product by its name
	 */
//	@RequestMapping("/productname/{name}")
//	public String searchForProductByName(@PathVariable(value = "name") String name, Model model) throws Exception {
//		if (name == null) {
//			throw new Exception("No product found");
//		}
//		model.addAttribute("product", productService.getSpecificProduct(name));
//		return "product";
//	}

}
