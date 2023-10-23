package com.backend.OrderHere.dto.Signin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDTO {
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
