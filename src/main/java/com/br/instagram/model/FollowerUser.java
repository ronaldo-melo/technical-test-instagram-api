package com.br.instagram.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FollowerUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long FollowerUserId; // ID DO USUÁRIO QUE ELE IRÁ SEGUIR

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false) // ID DO USUÁRIO SEGUIDOR
	private User followerUser;

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

	public User getFollowerUser() {
		return followerUser;
	}

	public void setFollowerUser(User followerUser) {
		this.followerUser = followerUser;
	}

}
