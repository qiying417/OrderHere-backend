package com.backend.OrderHere.dto.Signin;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

@Getter
@Setter
public class SignupResponseDTO {
    private int userId;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private ZonedDateTime createdTime;
    private ZonedDateTime updatedTime;

}
