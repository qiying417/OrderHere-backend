package com.backend.OrderHere.filter;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email; //username
    private String password;

}
