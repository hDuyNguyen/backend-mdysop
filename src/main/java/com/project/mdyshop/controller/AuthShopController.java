package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateShopRequest;
import com.project.mdyshop.dto.request.SigninRequest;
import com.project.mdyshop.dto.request.SignupRequest;
import com.project.mdyshop.dto.response.AuthResponse;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.AuthService;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shop/auth")
public class AuthShopController {

    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody SigninRequest request) {
        return authService.signin(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody SignupRequest request) throws UserException {
        return authService.signup(request);
    }

    @PostMapping("/newShop")
    public ResponseEntity<Shop> createShop(@RequestBody CreateShopRequest request,
                                           @RequestHeader("Authorization")String jwt)
            throws UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.createShop(request, user);

        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }
}
