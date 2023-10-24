package com.backend.OrderHere.filter;

import com.backend.OrderHere.config.SecurityConfig;
import com.backend.OrderHere.config.StaticConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Slf4j
public class JwtVerifyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        //retrieve response authorization header
        log.debug("JwtVerifyFilter -> doFilterInternal() start");
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        //proceed to next filter if authorizationHeader is empty or not start with prefix
        if(authorizationHeader == null || !authorizationHeader.startsWith(StaticConfig.JwtPrefix)){
            filterChain.doFilter(request, response);
            return;
        }

        //trim, verify token(include token expiration) and extract payload
        String token = authorizationHeader.replace(StaticConfig.JwtPrefix, "").trim();
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(StaticConfig.JwtSecretKey.getBytes()))
                .build()
                .parseSignedClaims(token);

        Claims claimsJwsPayload = claimsJws.getPayload();
        Integer userId = Integer.parseInt(claimsJwsPayload.getSubject());

        //get authroization detail
        var authorities = (List< Map<String, String>>) claimsJwsPayload.get("authorities");
        Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                .map(map -> new SimpleGrantedAuthority(map.get("authority")))
                .collect(Collectors.toSet());


        //generate Authentication and pass it to SecurityContextHolder
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userId,
                null,
                grantedAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        //go next filter
        filterChain.doFilter(request, response);
    }
}
