package com.backend.OrderHere.controller.v1;

import com.backend.OrderHere.dto.Signin.SignupRequestDTO;
import com.backend.OrderHere.dto.Signin.SignupResponseDTO;
import com.backend.OrderHere.dto.UserProfileUpdateDTO;
import com.backend.OrderHere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }
  @PutMapping("/{userId}/profile")
  public ResponseEntity<UserProfileUpdateDTO> updateProfile(@PathVariable Integer userId, @RequestBody UserProfileUpdateDTO dto) {
    UserProfileUpdateDTO updatedUserProfile = userService.updateUserProfile(userId, dto);
    return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
  }

  @RequestMapping("/signup")
  public ResponseEntity<SignupResponseDTO> createUser(@RequestBody SignupRequestDTO signUpPostDto){
    SignupResponseDTO createdUser = userService.createUser(signUpPostDto);
    return new ResponseEntity<SignupResponseDTO>(createdUser, HttpStatus.OK);
  }


}
