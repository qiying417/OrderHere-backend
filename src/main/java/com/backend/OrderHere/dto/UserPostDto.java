package com.backend.OrderHere.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class UserPostDto {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
}
