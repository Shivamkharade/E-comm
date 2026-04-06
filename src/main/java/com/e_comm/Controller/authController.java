package com.e_comm.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.e_comm.security.LoginRequestdto;
import com.e_comm.security.authService;
import com.e_comm.security.loginResponsdto;


@RestController
@RequestMapping("/auth")
public class authController {

	private final authService authService;
	
	public authController(authService authService1) {
		this.authService = authService1;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<loginResponsdto> login(@RequestBody LoginRequestdto loginRequestdto ){
		return ResponseEntity.ok(authService.login(loginRequestdto));
	}
	
}
