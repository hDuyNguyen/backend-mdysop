package com.project.mdyshop.controller;

import com.project.mdyshop.model.Order;
import com.project.mdyshop.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/order")
@PreAuthorize("hasRole('ADMIN')")
public class AdminOrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrder();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getUserOrder(@PathVariable Long orderId) {
        Order order = orderService.getUserOrder(orderId);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
