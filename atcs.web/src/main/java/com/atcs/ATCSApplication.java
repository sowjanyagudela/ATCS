package com.atcs;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.atcs"})
public class ATCSApplication extends SpringBootServletInitializer {

//	public static void main(String[] args) {
//	SpringApplication.run(ATCSApplication.class, args);
//
//	}
//	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ATCSApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ATCSApplication.class, args);
	}

}
