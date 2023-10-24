package com.backend.OrderHere.service;

import com.backend.OrderHere.config.SecurityConfig;
import com.backend.OrderHere.config.StaticConfig;
import com.backend.OrderHere.dto.login.JwtResponseDTO;
import com.backend.OrderHere.dto.login.LoginRequestDTO;
import com.backend.OrderHere.exception.ResourceNotFoundException;
import com.backend.OrderHere.exception.UnAuthorizedException;
import com.backend.OrderHere.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;


    /**
     * retrieve user details from database and generate JWT token if user authenticated
     * */

    public JwtResponseDTO retrieveUser(LoginRequestDTO loginRequestDTO) {
        User savedUser = userService.findByEmail(loginRequestDTO);
        if(savedUser == null){
            throw new ResourceNotFoundException("user not found");
        }
        if(savedUser.getPassword().equals(loginRequestDTO.getPassword())){
            String jwt_token = Jwts.builder()
                    .subject(savedUser.getUserId().toString())
//                    .claim("userName", savedUser.getUsername())
                    .claim("authorities", savedUser.getUserRole())
                    .issuedAt(new Date())
                    .expiration(Date.from(Instant.now().plusMillis(24*60*60*1000))) //24 hours expiration
                    .signWith(Keys.hmacShaKeyFor(StaticConfig.JwtSecretKey.getBytes()))
                    .compact();
            return new JwtResponseDTO(jwt_token);
        }
        throw new UnAuthorizedException("Invalid password or email");
    }






}
