package com.spring.is.here.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.is.here.service.HelloWorldService;

@RestController
public class SpringIsHereController {

	private HelloWorldService helloWorldService;
	
	@Autowired
	public SpringIsHereController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}
	
	@RequestMapping("/")
	public String sayHelloWorld() {
		return helloWorldService.sayHello();
	}
	
}
