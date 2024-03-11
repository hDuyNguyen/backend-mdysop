package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.SigninRequest;
import com.project.mdyshop.dto.response.AuthResponse;
import com.project.mdyshop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/auth")
public class AuthAdminController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody SigninRequest request) {
        return authService.signin(request);
    }
}
