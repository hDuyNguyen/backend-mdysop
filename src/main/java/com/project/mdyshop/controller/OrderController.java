package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateOrderRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Order;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.OrderService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/order")
@PreAuthorize("hasRole('USER')")
public class OrderController {

    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestHeader("Authorization")String jwt,
                                             @RequestBody CreateOrderRequest request)
            throws UserException, CouponException {

        User user = userService.findUserByToken(jwt);

        Order order = orderService.createOrder(user, request);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
