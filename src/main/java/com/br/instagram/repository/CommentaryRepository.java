package com.br.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.instagram.model.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

}
