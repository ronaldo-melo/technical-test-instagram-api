package com.br.instagram.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.instagram.model.Commentary;
import com.br.instagram.model.Publication;
import com.br.instagram.model.User;
import com.br.instagram.repository.CommentaryRepository;
import com.br.instagram.repository.PublicationRepository;
import com.br.instagram.repository.UserRepositoty;

@RestController
@RequestMapping("/publications")
public class PublicationController {
	
	@Autowired
	private UserRepositoty userRepositoty;
	
	@Autowired
	private CommentaryRepository commentaryRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@PostMapping("/add-commentary")
	public ResponseEntity<?> addCommentInPublication(@RequestBody Commentary commentary){
		 
		Optional<Publication> publicationOptional = publicationRepository.findById(commentary.getPublication().getId());
		
		if(publicationOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		Optional<User> userOptional = userRepositoty.findById(commentary.getUser().getId());
		
		if(userOptional.isEmpty()) {
			return ResponseEntity
					.badRequest()
					.body("Um usuário que não esta cadastrado não pode adicionar comentários nesta publicação");
		}
		
		commentary = commentaryRepository.save(commentary);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(commentary);
	}
	
}
