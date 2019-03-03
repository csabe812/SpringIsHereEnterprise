package com.spring.is.here;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@EnableConfigurationProperties
@SpringBootApplication
public class SpringIsHereApplication {

	public static void main(String[] args) {
		ApplicationContext ct = SpringApplication.run(SpringIsHereApplication.class, args);
		System.out.println(ct.getBean("person"));
	}

}
