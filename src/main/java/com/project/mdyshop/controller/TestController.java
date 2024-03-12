package com.project.mdyshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/anon")
    public String anonEndPoint() {
        return "Everyone can see this";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndPoint() {
        return "Only users can see this";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndPoint() {
        return "Only admin can see this";
    }

    @GetMapping("/shop")
    @PreAuthorize("hasRole('SHOP')")
    public String shopEndPoint() {
        return "Only shop can see this";
    }
}
