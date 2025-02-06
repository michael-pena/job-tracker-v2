package com.mpena.jobtrackerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jobtrackerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jobtrackerv2Application.class, args);
	}

	//TODO: Add the swaggerui - springrest docs
	//TODO: Implement the error handling - bad requests, invalid data http methods 400/500 - send concise response with status code and message
	//TODO: Add better tests, test every layer - add testcontainers for IT tests
	//TODO: add spring security - user auth with jwt
	
}
