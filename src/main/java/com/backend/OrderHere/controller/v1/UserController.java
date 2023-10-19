package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.UserGetDto;
import com.backend.OrderHere.dto.UserPostDto;
import com.backend.OrderHere.model.User;
import com.backend.OrderHere.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/public")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @RequestMapping("/signup")
    public ResponseEntity<UserGetDto> registerUser(@RequestBody UserPostDto userPostDto){

        logger.debug("Received registration request from user: {}", userPostDto);

        //create user and store in db
        UserGetDto newUser = userService.registerUser(userPostDto);

        //generate log and response HTTP request
        logger.debug("User registered successfully: {}", newUser);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }


    @GetMapping("hello")
    public String responseHello(){
        return "Hello";
    }

    @RequestMapping("/test")
    public String replytoConsole(){
        return ("Post Method is fine, no problem at all");
    }

}
