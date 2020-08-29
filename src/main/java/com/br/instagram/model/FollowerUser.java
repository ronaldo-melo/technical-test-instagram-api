package com.br.instagram.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class FollowerUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long FollowerUserId; // ID DO USUÁRIO QUE ELE IRÁ SEGUIR

	public Long getFollowerUserId() {
		return FollowerUserId;
	}

	public void setFollowerUserId(Long followerUserId) {
		FollowerUserId = followerUserId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
