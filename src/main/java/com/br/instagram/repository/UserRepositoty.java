package com.br.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.instagram.model.User;

@Repository
public interface UserRepositoty extends JpaRepository<User, Long> {

}
