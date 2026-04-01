package com.e_comm.config;

import com.e_comm.Repository.UserRepository;
import com.e_comm.entity.UserEntity;

public class UserConfiguration {

	private final UserRepository repository;
	
	public UserConfiguration (UserRepository repository1) {
		this.repository = repository1;
	}
	
	public String username(String userName) {
		UserEntity user = repository.findByUsername(userName);
		return user.getUsername()  ;
	}
}
