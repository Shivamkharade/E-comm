package com.e_comm.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.e_comm.Repository.UserRepository;

@Service
public class UserConfiguration implements UserDetailsService {

	private final UserRepository repository;
	
	public UserConfiguration (UserRepository repository1) {
		this.repository = repository1;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return repository.findByUsername(username).orElseThrow();
	}
	
}
