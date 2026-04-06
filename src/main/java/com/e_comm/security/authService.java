package com.e_comm.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.e_comm.entity.UserEntity;

@Service
public class authService {
	
	private final AuthenticationManager authenticationManager;
	
	public authService(AuthenticationManager authenticationManager1) {
		this.authenticationManager = authenticationManager1;
	}

	public loginResponsdto login(LoginRequestdto loginRequestdto) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestdto.getUsername(), loginRequestdto.getPassword())
				); 
		
		UserEntity user = (UserEntity) authentication.getPrincipal();
		
		return null;
	}

}
