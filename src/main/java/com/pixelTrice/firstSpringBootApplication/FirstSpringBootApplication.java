package com.pixelTrice.firstSpringBootApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
public class FirstSpringBootApplication  {

	public static void main(String[] args) {
		SpringApplication.run(FirstSpringBootApplication.class, args);
	}
	
	 @RequestMapping(value = "/products")
	   public String getProductName() {
	      return "Honey";   
	   }
     

}