package com.backend.OrderHere.filter;

import com.backend.OrderHere.config.StaticConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class JwtCredentialsAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;
    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {


        //extract credentials from request and map to authenticationRequest object
        AuthenticationRequest authenticationRequest = new ObjectMapper().readValue(
                request.getInputStream(), AuthenticationRequest.class
        );

        //create authentication token
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        );

        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    @SneakyThrows
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult){
        //generate token
        String token = Jwts.builder()
                .subject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plusMillis(24*60*60*1000))) //24 hours expiration
                .signWith(Keys.hmacShaKeyFor(StaticConfig.JwtSecretKey.getBytes()))
                .compact();

        //add to HttpServletResponse
        response.addHeader(HttpHeaders.AUTHORIZATION, StaticConfig.JwtPrefix + token);
    }



}
