package com.project.mdyshop.controller;

import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.AddressService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService;

    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllUserAddress(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);
        List<Address> addresses = addressService.getUserAddress(user.getId());

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
