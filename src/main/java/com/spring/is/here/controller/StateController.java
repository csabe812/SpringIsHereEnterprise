package com.spring.is.here.controller;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.is.here.repository.ShopRepository;
import com.spring.is.here.repository.StateRepository;
import com.spring.is.here.repository.UserRepository;
import com.spring.is.here.service.ProductService;
import com.spring.is.here.service.StateService;

@Controller
@RequestMapping("/state")
public class StateController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	private StateService stateService;
	private ProductService productService;
	
	@Autowired
	public StateController(StateService stateService, ProductService productService) {
		this.stateService = stateService;
		this.productService = productService;
	}
	
	@RequestMapping("")
	public String states(Model model) {
		model.addAttribute("states", this.stateService.getStates());
		System.out.println(this.stateService.getStates().get(0).toString());
		return "states";
	}
	
	@RequestMapping(path = "/{id}/products", method = RequestMethod.GET)	
	public String productsByStateId(@PathVariable("id") long id, Model model) {
		log.info("idka: " + id);
		model.addAttribute("products", this.productService.findAllByStateId(id));
		return "products";
	}

	
}
