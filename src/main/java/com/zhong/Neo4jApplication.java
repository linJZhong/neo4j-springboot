package com.zhong;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class Neo4jApplication extends SpringBootServletInitializer{


	public static void  main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Neo4jApplication.class);
	}
	
	


}
