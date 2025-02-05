package com.mpena.jobtrackerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jobtrackerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jobtrackerv2Application.class, args);
	}

	//TODO: Implement paging and sorting
	//TODO: Implement validation on Entities
	//TODO: Implement the error handling - bad requests, invalid data http methods 400/500
	//TODO: Add the swaggerui - springrest docs
	//TODO: add spring security - user auth with jwt
	
}
