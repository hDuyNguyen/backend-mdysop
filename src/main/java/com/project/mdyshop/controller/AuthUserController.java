package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.AddressRequest;
import com.project.mdyshop.dto.request.SigninRequest;
import com.project.mdyshop.dto.request.SignupRequest;
import com.project.mdyshop.dto.response.AuthResponse;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.AddressService;
import com.project.mdyshop.service.AuthService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/auth")
public class AuthUserController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody SigninRequest request){
        return authService.signin(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody SignupRequest request) throws UserException {
        return authService.signup(request);
    }

//    @GetMapping("/profile")
//    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization")String jwt) throws UserException {
//        User user =userService.findUserByToken(jwt);
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

    @PostMapping("/address/new")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Address> saveAddress(@RequestHeader("Authorization")String jwt, @RequestBody AddressRequest request) throws UserException {
        User user = userService.findUserByToken(jwt);

        Address address = addressService.createAddress(request, user);

        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }
 }
