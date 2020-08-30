package com.br.instagram.controller;

import java.util.ArrayList;
import java.util.List;

import com.br.instagram.model.FollowedUser;
import com.br.instagram.model.FollowerUser;
import com.br.instagram.model.User;

public class UserDTO {

	private String name;

	private String userName;

	private List<FollowedUserDTO> followedsDTO = new ArrayList<>();

	private List<FollowerUserDTO> followersDTO = new ArrayList<>();

	public UserDTO(User user) {
		this.name = user.getName();
		this.userName = user.getUserName();
		addFollowerUserDTO(user.getFollowing());
		addFollowedUserDTO(user.getFollowers());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void addFollowerUserDTO(List<FollowerUser> followers) {

		followers.forEach(f -> followedsDTO
				.add(new FollowedUserDTO(f.getFollowerUser().getName(), f.getFollowerUser().getUserName())));

	}

	public void addFollowedUserDTO(List<FollowedUser> followers) {

		followers.forEach(f -> followersDTO
				.add(new FollowerUserDTO(f.getFollowedUser().getName(), f.getFollowedUser().getUserName())));

	}

}
