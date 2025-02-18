package com.mpena.jobtrackerv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.mpena.jobtrackerv2.config.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class Jobtrackerv2Application {

	public static void main(String[] args) {
		SpringApplication.run(Jobtrackerv2Application.class, args);
	}

	//TODO: add created and lastupdated to user and application entities
	//TODO: add validation on DTOs and Entities
	//TODO: configure the swagger pages to get a token for making requests - secure it
	//TODO: add jacoco test reports
	//TODO: update the readme
	//TODO: Add better tests, test every layer - add testcontainers for IT tests
}
