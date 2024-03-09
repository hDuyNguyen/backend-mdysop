package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.SigninRequest;
import com.project.mdyshop.dto.request.SignupRequest;
import com.project.mdyshop.dto.response.AuthResponse;
import com.project.mdyshop.exception.UserException;

public interface AuthService {

    AuthResponse signin(SigninRequest request);
    AuthResponse signup(SignupRequest request) throws UserException;
}
