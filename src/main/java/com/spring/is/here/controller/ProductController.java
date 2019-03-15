package com.spring.is.here.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.is.here.domain.Product;
import com.spring.is.here.domain.Shop;
import com.spring.is.here.repository.ProductRepository;
import com.spring.is.here.repository.ShopRepository;

@Controller
@RequestMapping("/shop")
public class ProductController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ShopRepository shopRepository;
	private ProductRepository productRepository;

	@Autowired
	public ProductController(ShopRepository shopRepository, ProductRepository productRepository) {
		this.shopRepository = shopRepository;
		this.productRepository = productRepository;
	}

	@RequestMapping(value = "/{id}/products", method = RequestMethod.GET)
	public String getProductsByShop(@PathVariable("id") long id, Model model) {
		model.addAttribute("products", productRepository.findByShopId(id));
		model.addAttribute("shop", shopRepository.findById(id));
		return "products";
	}

	@RequestMapping(value = "/{id}/products/addnewproduct", method = RequestMethod.GET)
	public String showNewProductForm(@PathVariable("id") long id, Product product, Shop shop) {
		return "add-product";
	}

	@RequestMapping(value = "/{id}/products/addnewproduct/add", method = RequestMethod.POST)
	public String addNewProduct(@PathVariable("id") long id, Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-product";
		}
		log.info(product.toString());
		log.info("ID: " + id);
		Product p = product;
		Shop s = this.shopRepository.findById(id);
		p.setShop(s);
		this.productRepository.save(p);
		model.addAttribute("products", this.productRepository.findByShopId(id));
		model.addAttribute("shop", s);
		return "products";
	}

	@RequestMapping(value = "/{shopid}/products/edit/{id}", method = RequestMethod.GET)
	public String shopUpdateProductForm(@PathVariable("shopid") long shopid, @PathVariable("id") long productid,
			Model model) {
		Product product = productRepository.findById(productid)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + productid));
		model.addAttribute("product", product);
		return "update-product";
	}

	@RequestMapping(value = "/{shopid}/products/update/{id}", method = RequestMethod.POST)
	public String updateProduct(@PathVariable("shopid") long shopid, @PathVariable("id") long productid,
			@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			product.setId(productid);
			log.info("Error occured during updating a product");
			return "update-product";
		}
		log.info("id " + productid);
		log.info(product.toString());
		productRepository.save(product);
		model.addAttribute("products", productRepository.findByShopId(shopid));
		model.addAttribute("shop", shopRepository.findById(shopid));
		return "products";
	}

	@RequestMapping(value = "/{shopid}/products/delete/{productid}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("shopid") long shopid, @PathVariable("productid") long productid,
			Model model) {
		log.info("Shopid: " + shopid);
		log.info("Productid: " + productid);
		Product product = productRepository.findById(productid)
				.orElseThrow(() -> new IllegalArgumentException("Invalid productid Id:" + productid));
		productRepository.delete(product);
		model.addAttribute("products", productRepository.findByShopId(shopid));
		model.addAttribute("shop", shopRepository.findById(shopid));
		return "products";
	}
}
