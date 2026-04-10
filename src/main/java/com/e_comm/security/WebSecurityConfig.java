package com.e_comm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
	        .authorizeHttpRequests(auth -> auth
	                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ✅ FIX
	                .requestMatchers(
	                        "/swagger-ui/**",
	                        "/v3/api-docs/**",
	                        "/swagger-resources/**"
	                ).permitAll()
	                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
	                .requestMatchers(HttpMethod.POST, "/auth/signup").permitAll()
	                .requestMatchers(HttpMethod.POST, "/auth/signup/admin").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.GET,"/products/**").permitAll()
	                .requestMatchers(HttpMethod.POST,"/products").hasRole("ADMIN")
	                .requestMatchers(HttpMethod.DELETE,"/products/**").hasRole("ADMIN")
	                .requestMatchers("/orders/**","/cart/**").hasAnyRole("USER","ADMIN")
	                .requestMatchers("/products/**").hasRole("ADMIN")
	                .anyRequest().authenticated()
	        )
	        .sessionManagement(sessionconfig -> 
	            sessionconfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(jwtauthFilter, UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
	@Bean
	public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
	    org.springframework.web.cors.CorsConfiguration configuration = new org.springframework.web.cors.CorsConfiguration();

	    configuration.setAllowedOrigins(java.util.List.of("*"));
	    configuration.setAllowedMethods(java.util.List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
	    configuration.setAllowedHeaders(java.util.List.of("*"));

	    org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
	            new org.springframework.web.cors.UrlBasedCorsConfigurationSource();

	    source.registerCorsConfiguration("/**", configuration);
	    return source;
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
