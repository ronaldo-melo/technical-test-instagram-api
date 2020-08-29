package com.br.instagram.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GetMapping(value = "/{userId}")
	public ResponseEntity<User> findAll(@PathVariable Long userId) {

		Optional<User> userOptinal = userRepository.findById(userId);

		return ResponseEntity.of(userOptinal);

	}
	
	@PostMapping
	public ResponseEntity<User> save(@RequestBody User user) {
		user = userRepository.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}


}
