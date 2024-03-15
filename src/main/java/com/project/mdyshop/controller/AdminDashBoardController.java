package com.project.mdyshop.controller;

import com.project.mdyshop.model.User;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/dashboard")
@PreAuthorize("hasRole('ADMIN')")
public class AdminDashBoardController {

    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<User>> getALlUser() {
        List<User> users = userService.getAllUser();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
