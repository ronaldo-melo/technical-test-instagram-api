package com.br.instagram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.instagram.model.User;
import com.br.instagram.repository.UserRepositoty;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserRepositoty userRepository;

	@GetMapping
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
}
