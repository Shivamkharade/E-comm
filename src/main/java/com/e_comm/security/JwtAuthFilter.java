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

    private static final Logger log = LoggerFactory.getLogger(JwtAuthFilter.class);

    public JwtAuthFilter(AuthUtil authUtil, UserDetailsService userDetailsService) {
        this.authUtil = authUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        log.info("Incoming request: {} {}", method, path);

        // ✅ 1. SKIP JWT for public endpoints + OPTIONS
        if (method.equals("OPTIONS") ||
            path.equals("/auth/login") ||
            path.equals("/auth/signup") ||
            path.startsWith("/swagger-ui") ||
            path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);
            return;
        }

        // ✅ 2. Extract JWT
        final String requestHeader = request.getHeader("Authorization");

        String jwt = null;
        String username = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            jwt = requestHeader.substring(7);

            try {
                username = authUtil.extractUsername(jwt);
            } catch (Exception e) {
                log.error("Invalid JWT token: {}", e.getMessage());
            }
        }

        // ✅ 3. Validate and set authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            var user = userDetailsService.loadUserByUsername(username);

            if (authUtil.isTokenValid(jwt, user)) {

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        // ✅ 4. Continue filter chain
        filterChain.doFilter(request, response);
    }
}