package com.spring.is.here.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.is.here.domain.Shop;
import com.spring.is.here.domain.User;
import com.spring.is.here.repository.UserRepository;
import com.spring.is.here.service.EmailService;
import com.spring.is.here.service.ProductService;
import com.spring.is.here.service.ShopService;
import com.spring.is.here.service.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private UserServiceImpl userService; 
	private UserRepository userRepository;
	
	@Autowired
	public AdminController(UserServiceImpl userService, UserRepository userRepository) {
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
	@GetMapping("/helloworld")
	public String helloworld() {
		log.info("Printing helloworld");
		return "helloworld";
	}
	
	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public String users(Model model) {
		model.addAttribute("users", this.userService.findAllUsers());
		return "users";
	}

	/**
	 * Showing the update form (update-shop.html)
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/edit/{id}", method = RequestMethod.GET)
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		//Shop shop = shopRepository.findById(id);
		//model.addAttribute("shop", shop);
		return "update-shop";
	}

	/**
	 * Pressing the update button updates the shop
	 * 
	 * @param id
	 * @param shop
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/update/{id}", method = RequestMethod.POST)
	public String updateShop(@PathVariable("id") long id, @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			user.setId(id);
			return "update-shop";
		}
		log.info(user.toString());
		//shopRepository.save(shop);
		//model.addAttribute("shops", shopRepository.findAll());
		return "shops";
	}

	/**
	 * Deleting a shop
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
	public String deleteShop(@PathVariable("id") long id, Model model) {
		User user = userRepository.findById(id);
		log.info(user.toString());
		userRepository.delete(user);
		model.addAttribute("users", userRepository.findAll());
		return "users";
	}
	
}
