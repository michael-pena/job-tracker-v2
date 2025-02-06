package com.mpena.jobtrackerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jobtrackerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jobtrackerv2Application.class, args);
	}

	//TODO: Fix the validator logic for accepted
	//TODO: Add better tests, test every layer - add testcontainers for IT tests
	//TODO: add jacoco test reports
	//TODO: add spring security - user auth with jwt
	
}
