package com.backend.OrderHere.dto.login;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class LoginRequestDTO {
    private String email;
    private String password;
}
