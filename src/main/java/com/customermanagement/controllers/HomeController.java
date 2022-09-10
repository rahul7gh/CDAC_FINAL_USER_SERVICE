package com.customermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customermanagement.config.ServiceConfig;
import com.customermanagement.service_communication.ProducService;

@RestController
public class HomeController {

	@Autowired
	ServiceConfig config;
	
	@Autowired
	ProducService service;
	
	@Value("${spring.jpa.database}")
	String db; 
	@GetMapping("/")
	public String hello()
	{
		return db+" "+config.getProperty()+" "+service.hello() ;
	}
}
