package com.e_comm.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_comm.Repository.UserRepository;
import com.e_comm.entity.UserEntity;

@Service
public class UserConfiguration implements UserDetailsService {

	private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	public UserConfiguration (UserRepository repository1,PasswordEncoder encoder1) {
		this.repository = repository1;
		this.encoder = encoder1;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserEntity user = repository.findByUsername(username).orElseThrow();
		return User.builder()
					.username(user.getUsername())
					.password(encoder.encode(user.getPassword()))
					.roles(user.getRole())
					.build();
	}
	
}
