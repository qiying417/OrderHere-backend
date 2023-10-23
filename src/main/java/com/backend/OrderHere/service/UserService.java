package com.backend.OrderHere.service;

import com.backend.OrderHere.dto.Signin.SignupRequestDTO;
import com.backend.OrderHere.dto.Signin.SignupResponseDTO;
import com.backend.OrderHere.dto.UserProfileUpdateDTO;
import com.backend.OrderHere.dto.login.LoginRequestDTO;
import com.backend.OrderHere.exception.DataIntegrityException;
import com.backend.OrderHere.exception.ResourceNotFoundException;
import com.backend.OrderHere.mapper.UserMapper;
import com.backend.OrderHere.model.User;
import com.backend.OrderHere.model.enums.UserRole;
import com.backend.OrderHere.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Autowired
  public UserService(UserRepository userRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserProfileUpdateDTO updateUserProfile(Integer userId, UserProfileUpdateDTO dto) {
    User user = userRepository.findById(userId)
      .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    try {
      userMapper.updateUserFromUserProfileUpdateDTO(dto, user);
      User updatedUser = userRepository.save(user);

      return userMapper.userToUserProfileUpdateDTO(updatedUser);
    } catch (DataIntegrityViolationException e) {
      throw new DataIntegrityException("Username already exists");
    } catch (Exception e) {
      throw new RuntimeException("Something went wrong");
    }
  }

  public SignupResponseDTO createUser(SignupRequestDTO signUpPostDto) {
    User newUser = User.builder()
            .username(signUpPostDto.getUserName())
            .firstname(signUpPostDto.getFirstName())
            .lastname(signUpPostDto.getLastName())
            .email(signUpPostDto.getEmail())
            .password(signUpPostDto.getPassword())
            .point(0)
            .avatarUrl("default.com")
            .userRole(UserRole.customer)
            .build();
    User createdUser = userRepository.save(newUser);
    return userMapper.userToSignupResponseDTO(createdUser);
  }

  public Optional<User> findByEmail(LoginRequestDTO loginRequestDTO) {
    return userRepository.findByEmail(loginRequestDTO.getEmail());
  }
}


