package com.mpena.jobtrackerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Jobtrackerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jobtrackerv2Application.class, args);
	}

	//TODO: add users service and user controller
	//TODO: change Ids from int to long in Application
	//TODO: add lookup by username, authentication, update password, etc.
	//TODO: add created and lastupdated to user and application entities
	//TODO: add users validation on DTOs and Entity

	//TODO: add spring security - user auth with jwt
	//TODO: add users login and registration with spring security
	//TODO: add jacoco test reports
	//TODO: Add better tests, test every layer - add testcontainers for IT tests
}
