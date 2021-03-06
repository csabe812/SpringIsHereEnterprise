/**
 * COPYRIGHTS AND OTHER STUFFS GOES HERE
 */
package com.spring.is.here.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.is.here.domain.User;
import com.spring.is.here.service.EmailService;
import com.spring.is.here.service.ProductService;
import com.spring.is.here.service.ShopService;
import com.spring.is.here.service.UserServiceImpl;

/**
 * This is the HomeController class a.k.a. the main page a.k.a index
 * 
 * @author csabe812
 *
 */
@Controller
public class HomeController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private ProductService productService;
	private ShopService shopService;
	private UserServiceImpl userService;
	private EmailService emailService;

	/**
	 * Constructor
	 * 
	 * @param productService
	 */
	@Autowired
	public HomeController(ProductService productService, ShopService shopService, UserServiceImpl userService,
			EmailService emailService) {
		this.productService = productService;
		this.shopService = shopService;
		this.userService = userService;
		this.emailService = emailService;
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

	@RequestMapping("/tesztoldal")
	public String tesztoldal() {
		return "start";
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

	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String reg(User user) {
		return "registration";
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public String regist(@ModelAttribute User user) {
		log.info("New user");
		emailService.sendMessage(user.getEmail());
		log.debug(user.getFullname());
		log.debug(user.getEmail());
		log.debug(user.getPassword());
		userService.registerUser(user);
		return "auth/login";
	}

	@RequestMapping(value = "/admin/addshopowner", method = RequestMethod.GET)
	public String addShopOwner(User user) {
		return "shopownerregistration";
	}

	@RequestMapping(value = "/admin/addnewshopowner", method = RequestMethod.POST)
	public String addNewShopOwner(@ModelAttribute User user) {
		log.info("New user");
		userService.registerShopOwner(user);
		return "auth/login";
	}

	@RequestMapping(path = "/activation/{code}", method = RequestMethod.GET)
	public String activation(@PathVariable("code") String code, HttpServletResponse response) {
		String result = userService.userActivation(code);
		return "auth/login";
	}
}
