package com.customermanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.customermanagement.config.ServiceConfig;
import com.customermanagement.pojo.User;
import com.customermanagement.service.IUserService;
import com.customermanagement.service_communication.ProducService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HomeController {

	@Autowired
	ServiceConfig config;
	
	@Autowired
	ProducService service;
	
	@Autowired
	IUserService userService;

	@Autowired
	ObjectMapper mapper;
	
	@GetMapping("/")
	public String hello()
	{
		return config.getProperty();
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> addNewUser(@RequestBody User newUser)
	{
		
		return ResponseEntity.ok(userService.addNewUser(newUser));
	}
}
