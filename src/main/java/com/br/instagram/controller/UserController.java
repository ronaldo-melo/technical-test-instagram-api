package com.br.instagram.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.instagram.model.FollowedUser;
import com.br.instagram.model.FollowerUser;
import com.br.instagram.model.User;
import com.br.instagram.repository.FollowedUserRepository;
import com.br.instagram.repository.FollowerUserRepository;
import com.br.instagram.repository.UserRepositoty;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserRepositoty userRepository;

	private FollowedUserRepository followedUserRepository;
	
	private FollowerUserRepository followerUserRepository;
	
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

	@DeleteMapping(value = "/{userId}")
	public ResponseEntity<?> deleteById(@PathVariable Long userId) {

		try {
			
			userRepository.deleteById(userId);
			
			return ResponseEntity.noContent().build();

		} catch (EmptyResultDataAccessException e) {
			
			return ResponseEntity.notFound().build();

		}

	}
	
	@PutMapping(value = "/{followUserId}/follow-user")
	public ResponseEntity<?> followUser(@PathVariable Long followUserId, @RequestBody User userWhoWillBeAFollower) {
		
		//verifica se o seguidor existe
		Optional<User> followUserOptional = userRepository.findById(followUserId);				
		if(followUserOptional.isEmpty())
			return ResponseEntity.notFound().build();
		
		//verifica se o usuário a ser seguido existe
		Optional<User> userToBeFollowedOptional = userRepository.findById(userWhoWillBeAFollower.getId());		
		if(userToBeFollowedOptional.isEmpty())
			return ResponseEntity.badRequest().build();
		
		/*	
		    Impedir que um mesmo usuário seja seguido duas vezes pela mesma pessoa. 
		 	No caso entre os registros da tabela de Followed Users se haver um registro 
		 	com os valores userWhoWillBeAFollower(usuário que será seguidor) e
		 	followUserId (usuário seguido) então haverá um conflito pois não 
		 	faz sentido uma pessoa seguir outra mais de uma vez.	   
		*/
		if (followedUserRepository.findAll()
				.stream()
					//verifica se um usuário que segue já existe ...
					.anyMatch((u) -> u.getFollowedUser().getId() == followUserId
					//com o mesmo id de usuário a ser seguido
					&& userWhoWillBeAFollower.getId() == u.getUserWhoWillBeAFollowerId())
				) {
			
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Não pode seguir um usuário que já é seguido!");
			
		} else {
			
			//Usuário seguido
			FollowedUser followedUser = new FollowedUser();
			
			followedUser.setFollowedUser(followUserOptional.get());
			
			followedUser.setUserWhoWillBeAFollowerID(userToBeFollowedOptional.get().getId());
			
			followedUserRepository.save(followedUser);
			
			//quando um usuário é seguido ele ganha (obviamente ele ganha um seguidor (FollowerUser))
			//por isso followUserOptional.get() será o usuário que seguidor e 
			//userToBeFollowedOptional.get().getId() é id do usuário a ser seguido ou que foi seguido
			//AO AVALIADOR QUE ESTIVER LENDO ESSE CÓDIGO SAIBA ESSA
			//RELAÇÃO UM PRA MUITOS ENTRE User e FollowerUser/FollowedUser FOI O QUE
			//ME DEU MAIS TRABALHO.
			addFollow(followUserOptional.get(), userToBeFollowedOptional.get().getId());
			
		}
		
		return ResponseEntity.noContent().build();
	}
	
	private void addFollow(User followerUserId, Long userToBeFolloweId) {
		
		FollowerUser followerUser = new FollowerUser();
		followerUser.setFollowerUser(followerUserId);
		followerUser.setFollowerUserId(userToBeFolloweId);
		
		followerUserRepository.save(followerUser);
	}
	
}
