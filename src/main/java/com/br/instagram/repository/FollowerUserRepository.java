package com.br.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.instagram.model.FollowerUser;

public interface FollowerUserRepository extends JpaRepository<FollowerUser, Long> {

}
