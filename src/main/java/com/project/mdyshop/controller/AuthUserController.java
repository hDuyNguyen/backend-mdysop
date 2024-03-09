package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.SigninRequest;
import com.project.mdyshop.dto.request.SignupRequest;
import com.project.mdyshop.dto.response.AuthResponse;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/auth")
public class AuthUserController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody SigninRequest request){
        return authService.signin(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody SignupRequest request) throws UserException {
        return authService.signup(request);
    }
 }
