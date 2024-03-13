package com.project.mdyshop.controller;

import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.CartRepository;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
@PreAuthorize("hasRole('USER')")
public class CartController {

    @Autowired
    UserService userService;
    @Autowired
    CartRepository cartRepository;

    @GetMapping("/")
    public ResponseEntity<List<Cart>> findUserCart(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);
        List<Cart> cart = cartRepository.findCartByUserId(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
