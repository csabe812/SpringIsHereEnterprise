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

import com.spring.is.here.domain.Shop;
import com.spring.is.here.repository.ProductRepository;
import com.spring.is.here.repository.ShopRepository;

@Controller
@RequestMapping("/shop")
public class ShopController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	private ShopRepository shopRepository;
	private ProductRepository productRepository;

	@Autowired
	public ShopController(ShopRepository shopRepository, ProductRepository productRepository) {
		this.shopRepository = shopRepository;
		this.productRepository = productRepository;
	}

	@RequestMapping(value = "/addnewshop", method = RequestMethod.GET)
	public String showNewShopForm(Shop shop) {
		return "add-shop";
	}

	@RequestMapping(value = "/addshop", method = RequestMethod.POST)
	public String addUser(@Valid Shop shop, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-shop";
		}
		this.shopRepository.save(shop);
		model.addAttribute("shops", this.shopRepository.findAll());
		return "shops";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		Shop shop = shopRepository.findById(id);
		model.addAttribute("shop", shop);
		return "update-shop";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String updateShop(@PathVariable("id") long id, @Valid Shop shop, BindingResult result, Model model) {
		if (result.hasErrors()) {
			shop.setId(id);
			return "update-shop";
		}
		log.info(shop.toString());
		shopRepository.save(shop);
		model.addAttribute("shops", shopRepository.findAll());
		return "shops";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteShop(@PathVariable("id") long id, Model model) {
		Shop shop = shopRepository.findById(id);
		shopRepository.delete(shop);
		model.addAttribute("shops", shopRepository.findAll());
		return "shops";
	}
}