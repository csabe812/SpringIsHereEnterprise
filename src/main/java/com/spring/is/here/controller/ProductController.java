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

	@RequestMapping(value = "/{shopId}/products", method = RequestMethod.GET)
	public String getProductsByShop(@PathVariable("shopId") long shopId, Model model) {
		model.addAttribute("products", productRepository.findByShopId(shopId));
		model.addAttribute("shop", shopRepository.findById(shopId));
		return "products";
	}

	@RequestMapping(value = "/{shopId}/products/addproduct", method = RequestMethod.GET)
	public String showNewProductForm(Product product, @PathVariable("shopId") long id, Shop shop) {
		shop.setId(id);	
		return "add-product";
	}

	@RequestMapping(value = "/{shopId}/products/addnewproduct", method = RequestMethod.POST)
	public String addNewProduct(@Valid Product product, @PathVariable("shopId") long shopId, Shop shop, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-product";
		}
		log.info("shopid: " + shopId);
		log.info(product.toString());
		log.info(shop.toString());
		Product p = product;
		Shop s = this.shopRepository.findById(shopId);
		p.setShop(s);
		this.productRepository.save(p);
		model.addAttribute("products", this.productRepository.findByShopId(shopId));
		model.addAttribute("shop", s);
		return "products";
	}

	@RequestMapping(value = "/{shopId}/products/edit/{productId}", method = RequestMethod.GET)
	public String shopUpdateProductForm(@PathVariable("shopId") long shopId, @PathVariable("productId") long productId,
			Model model) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + productId));
		model.addAttribute("product", product);
		return "update-product";
	}

	@RequestMapping(value = "/{shopId}/products/update/{productId}", method = RequestMethod.POST)
	public String updateProduct(@PathVariable("shopId") long shopId, Shop shop, @PathVariable("productId") long productId,
			@Valid Product product, BindingResult result, Model model) {
		if (result.hasErrors()) {
			product.setId(productId);
			log.info("Error occured during updating a product");
			return "update-product";
		}
		log.info("shopid: " + shopId);
		log.info("productId: " + productId);
		log.info(product.toString());
		log.info(shop.toString());
		product.setId(productId);
		product.setShop(shopRepository.findById(shopId));
		productRepository.save(product);
		model.addAttribute("products", productRepository.findByShopId(shopId));
		model.addAttribute("shop", shopRepository.findById(shopId));
		return "products";
	}

	@RequestMapping(value = "/{shopId}/products/delete/{productId}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("shopId") long shopId, @PathVariable("productId") long productId,
			Model model) {
		log.info("Shopid: " + shopId);
		log.info("Productid: " + productId);
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid productid Id:" + productId));
		productRepository.delete(product);
		model.addAttribute("products", productRepository.findByShopId(shopId));
		model.addAttribute("shop", shopRepository.findById(shopId));
		return "products";
	}
}
