package com.br.instagram.dto;

import java.util.ArrayList;
import java.util.List;

import com.br.instagram.model.User;

public class UserDTO {

	private String name;

	private String userName;

	private Integer followeds;

	private Integer followers;

	private List<FollowedUserDTO> followedsList = new ArrayList<>();

	private List<FollowerUserDTO> followersList = new ArrayList<>();

	public UserDTO(User user, List<User> followeds, List<User> followers) {
		this.name = user.getName();
		this.userName = user.getUserName();
		addFollowerUserDTO(followers);
		addFollowedUserDTO(followeds);
		this.followeds = followeds.size();
		this.followers = followers.size();
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

	public Integer getFolloweds() {
		return followeds;
	}

	public void setFolloweds(Integer followeds) {
		this.followeds = followeds;
	}

	public Integer getFollowers() {
		return followers;
	}

	public void setFollowers(Integer followers) {
		this.followers = followers;
	}

	public List<FollowedUserDTO> getFollowedsList() {
		return followedsList;
	}

	public void setFollowedsList(List<FollowedUserDTO> followedsList) {
		this.followedsList = followedsList;
	}

	public List<FollowerUserDTO> getFollowersList() {
		return followersList;
	}

	public void setFollowersList(List<FollowerUserDTO> followersList) {
		this.followersList = followersList;
	}

	public void addFollowerUserDTO(List<User> userFollowers) {

		userFollowers.forEach(f -> followersList.add(new FollowerUserDTO(f.getName(), f.getUserName())));

	}

	public void addFollowedUserDTO(List<User> fuserFolloweds) {

		fuserFolloweds.forEach(f -> followedsList.add(new FollowedUserDTO(f.getName(), f.getUserName())));

	}

}
