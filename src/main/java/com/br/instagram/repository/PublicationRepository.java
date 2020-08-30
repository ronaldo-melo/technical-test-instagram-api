package com.br.instagram.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.instagram.model.Publication;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long>{

}
