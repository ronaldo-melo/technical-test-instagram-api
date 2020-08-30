package com.br.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.instagram.model.FollowedUser;

public interface FollowedUserRepository extends JpaRepository<FollowedUser, Long> {
	
}
