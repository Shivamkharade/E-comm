package com.e_comm.security;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final AuthUtil authUtil;
	
	private final UserDetailsService userDetailsService;

	
	public JwtAuthFilter(AuthUtil authUtil1,UserDetailsService userDetailsService1) {
		this.authUtil = authUtil1;
		this.userDetailsService = userDetailsService1;
	}
	
	private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("incoming request : {}",request.getRequestURL());
		
		final String requestHeader = request.getHeader("Authorization");
		String jwt = null;
		String username = null;
		
		if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
			jwt = requestHeader.substring(7);
			try {
			    username = authUtil.extractUsername(jwt);
			} catch (Exception e) {
			    log.error("Invalid JWT token");
			}
			
		}
		
		
		if (username !=null && SecurityContextHolder.getContext().getAuthentication() == null ) {
			
			var user = userDetailsService.loadUserByUsername(username);
			
			if (authUtil.isTokenValid(jwt, user)) {
				
				UsernamePasswordAuthenticationToken authenticationToken 
				= new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
}





