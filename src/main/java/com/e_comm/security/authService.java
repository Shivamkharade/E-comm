package com.e_comm.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.e_comm.Repository.UserRepository;
import com.e_comm.entity.UserEntity;

@Service
public class authService {
	
	private final AuthenticationManager authenticationManager;
	
	private final AuthUtil authUtil;
	
	private final UserRepository repository;
	
	
	public authService(AuthenticationManager authenticationManager1,AuthUtil authUtil1,UserRepository repository1) {
		this.authenticationManager = authenticationManager1;
		this.authUtil = authUtil1;
		this.repository = repository1;
	}

	public loginResponsdto login(LoginRequestdto loginRequestdto) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestdto.getUsername(), loginRequestdto.getPassword())
				); 
		
		UserEntity user = (UserEntity) authentication.getPrincipal();
		
		String tokan = authUtil.generateAccessToken(user);
		
		return new loginResponsdto(tokan,user.getId());
	}

	public SigenupResponsDto signup(LoginRequestdto sigenupRequestdto) {
		UserEntity entity = repository.findByUsername(sigenupRequestdto.getUsername()).orElse(null);
		
		if (entity != null) throw new IllegalArgumentException("User alredy exits");
		
		entity = repository.save(new UserEntity(sigenupRequestdto.getUsername(),sigenupRequestdto.getPassword(),sigenupRequestdto.getEmail()));
		return new SigenupResponsDto(entity.getId(), entity.getUsername());
	}

}
