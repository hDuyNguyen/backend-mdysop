package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.StatusOrderRequest;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Order;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.OrderService;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/order")
@PreAuthorize("hasRole('SHOP')")
public class ShopOrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;

    @PutMapping("/{orderId}")
    public ResponseEntity<Order> updateStatusOrder(@RequestBody StatusOrderRequest request,
                                                   @PathVariable Long orderId) {
        Order order = orderService.updateOrderStatus(orderId, request.getStatus());

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getShopOrders(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        List<Order> orders = orderService.getShopOrder(shop.getId());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getUserOrder(@PathVariable Long orderId) {
        Order order = orderService.getUserOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Order>> getUserOrderByStatus(@RequestHeader("Authorization")String jwt,
                                                            @RequestBody StatusOrderRequest request)
            throws UserException {

        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        List<Order> orders = orderService.getUserOrderByStatus(shop.getId(), request.getStatus());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
