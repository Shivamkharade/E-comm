package com.e_comm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {
	
	private final JwtAuthFilter jwtauthFilter;
	
	public WebSecurityConfig(JwtAuthFilter auth) {
		this.jwtauthFilter = auth;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception  {
		
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(sessionconfig -> sessionconfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/auth/**").permitAll()
					.requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated()
					)
			.addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class)
			.formLogin(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
