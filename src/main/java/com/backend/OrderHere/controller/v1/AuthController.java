//package com.backend.OrderHere.controller.v1;
//
//import com.backend.OrderHere.dto.login.JwtResponseDTO;
//import com.backend.OrderHere.dto.login.LoginRequestDTO;
//import com.backend.OrderHere.service.AuthService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AuthController {
//
//    private final AuthService authService;
//    //login by email and password
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponseDTO> userLogin(@RequestBody LoginRequestDTO loginRequestDTO){
//        JwtResponseDTO jwtResponseDTO = authService.retrieveUser(loginRequestDTO);
//        return new ResponseEntity<JwtResponseDTO>(jwtResponseDTO, HttpStatus.OK);
//    }
//}
