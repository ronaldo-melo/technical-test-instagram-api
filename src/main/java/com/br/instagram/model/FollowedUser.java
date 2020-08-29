package com.br.instagram.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FollowedUser { // USUÁRIO SEGUIDO

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long userWhoWillBeAFollowerId; // ID DO USUÁRIO SERÁ SEU SEGUIDOR

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserWhoWillBeAFollowerId() {
		return userWhoWillBeAFollowerId;
	}

	public void setUserWhoWillBeAFollowerID(Long userWhoWillBeAFollowerID) {
		this.userWhoWillBeAFollowerId = userWhoWillBeAFollowerID;
	}

}