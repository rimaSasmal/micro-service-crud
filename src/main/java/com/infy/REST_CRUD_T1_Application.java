package com.infy;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import com.infy.service.AccountCustomerService;

@SpringBootApplication
public class REST_CRUD_T1_Application {

	@Autowired
	AccountCustomerService accountCustomerService;

	@Autowired
	Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(REST_CRUD_T1_Application.class, args);
	}

}
