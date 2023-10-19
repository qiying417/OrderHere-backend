package com.backend.OrderHere.service;

import com.backend.OrderHere.dto.UserGetDto;
import com.backend.OrderHere.dto.UserPostDto;
import com.backend.OrderHere.model.User;
import com.backend.OrderHere.model.enums.UserRole;
import com.backend.OrderHere.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

        private final UserRepository userRepository;

        //register User information and store them in db
        public UserGetDto registerUser(UserPostDto userPostDto){

                User user = new User();
                user.setUsername(userPostDto.getUsername());
                user.setPassword(userPostDto.getPassword());
                user.setFirstname(userPostDto.getFirstname());
                user.setLastname(userPostDto.getLastname());
                user.setEmail(userPostDto.getEmail());
                user.setUserRoleEnum(UserRole.CUSTOMER);

                userRepository.save(user);

                UserGetDto userGetDto = new UserGetDto();
                userGetDto.setUsername(user.getUsername());
                userGetDto.setFirstname(user.getFirstname());
                userGetDto.setLastname(user.getLastname());
                userGetDto.setEmail(user.getEmail());

                return userGetDto;
        }






}
