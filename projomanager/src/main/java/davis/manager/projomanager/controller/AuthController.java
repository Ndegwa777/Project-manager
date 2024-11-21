package com.wera.wera.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wera.wera.entities.User;
import com.wera.wera.request.LoginRequest;
import com.wera.wera.response.AuthResponse;
import com.wera.wera.service.AuthService;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
   
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception{
        return new ResponseEntity<>(authService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest){
        return new ResponseEntity<>(authService.loginUser(loginRequest), HttpStatus.OK);
    }

        
}
