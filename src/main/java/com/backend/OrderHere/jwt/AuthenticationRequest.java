package com.backend.OrderHere.jwt;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email; //username
    private String password;
}
