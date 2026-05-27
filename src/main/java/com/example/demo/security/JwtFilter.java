package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import java.io.IOException;
import java.util.Collections;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("JWT FILTER RUNNING");
        System.out.println("AUTH HEADER = " + authHeader);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);

            System.out.println("TOKEN = " + token);

            if (jwtUtil.validateToken(token)) {

                String email = jwtUtil.extractEmail(token);

                System.out.println("EMAIL FROM TOKEN = " + email);

                User user = userRepository.findByEmail(email)
                        .orElseThrow();

                System.out.println("USER ID = " + user.getId());
                request.setAttribute("email", email);
                request.setAttribute("userId", user.getId());

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user.getId(),
                                null,
                                Collections.emptyList()
                        );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);
            } else {
                System.out.println("TOKEN INVALID");
            }
        } else {
            System.out.println("AUTH HEADER MISSING");
        }

        filterChain.doFilter(request, response);
    }
}