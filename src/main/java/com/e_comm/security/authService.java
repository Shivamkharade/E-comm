package com.e_comm.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.e_comm.Repository.UserRepository;
import com.e_comm.SolaceEventPublishing.EventPublisher;
import com.e_comm.SolaceEventPublishing.EventSent;
import com.e_comm.authdtos.LogInRequestDto;
import com.e_comm.authdtos.SigenupRequestdto;
import com.e_comm.authdtos.SigenupResponsDto;
import com.e_comm.authdtos.loginResponsdto;
import com.e_comm.entity.UserEntity;

@Service
public class authService {
	
	private final AuthenticationManager authenticationManager;
	
	private final AuthUtil authUtil;
	
	private final EventPublisher eventPublisher;
	
	private final UserRepository repository;
	
	private final PasswordEncoder encoder;
	
	
	public authService(AuthenticationManager authenticationManager1,AuthUtil authUtil1,UserRepository repository1,PasswordEncoder encoder1,EventPublisher eventPublisher1) {
		this.authenticationManager = authenticationManager1;
		this.authUtil = authUtil1;
		this.repository = repository1;
		this.encoder = encoder1;
		this.eventPublisher = eventPublisher1;
	}

	public loginResponsdto login(LogInRequestDto loginRequestdto) {
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequestdto.getUsername(), loginRequestdto.getPassword())
				); 
		
		UserEntity user = (UserEntity) authentication.getPrincipal();
		
		String tokan = authUtil.generateAccessToken(user);
		
		eventPublisher.publishLogInEvent(new EventSent(user.getId(), user.getUsername(), user.getEmail(), user.getRole()));
		
		return new loginResponsdto(tokan,user.getId());
	}

	
	
	
	public SigenupResponsDto signup(SigenupRequestdto sigenupRequestdto) {
		UserEntity entity = repository.findByUsername(sigenupRequestdto.getUsername()).orElse(null);
		
		if (entity != null) throw new IllegalArgumentException("User alredy exits");
		
		entity = repository.save( 
				new	UserEntity(sigenupRequestdto.getUsername(),  
							   sigenupRequestdto.getEmail(), 
							   encoder.encode(sigenupRequestdto.getPassword()), 
							   "USER"));
		eventPublisher.publishRegistrationEvent(
				new EventSent(entity.getId(), 
						entity.getUsername(), 
						entity.getEmail(), 
						entity.getRole()));
		return new SigenupResponsDto(entity.getId(), entity.getUsername());
	}

	
	
	
	public SigenupResponsDto signupAdmin(SigenupRequestdto adminRequestdto) {
		UserEntity entity = repository.findByUsername(adminRequestdto.getUsername()).orElse(null);
		if (entity !=null ) throw new RuntimeException("User alredy exits");
		
		entity = repository.save(
				new UserEntity(adminRequestdto.getUsername(), 
						adminRequestdto.getEmail(), 
						encoder.encode(adminRequestdto.getPassword()), 
						"ADMIN"));
		return new SigenupResponsDto(entity.getId(),entity.getUsername());
	}

}
