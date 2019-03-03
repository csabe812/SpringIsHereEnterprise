package com.spring.is.here;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringIsHereController {

	@RequestMapping("/")
	public String HelloWorld() {
		return "HelloWorld";
	}
	
}
