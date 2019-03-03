package com.spring.is.here.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Scope("prototype")
@Service
public class HelloWorldService {

	public String sayHello() {
		return "HelloWorld";
	}
	
}
