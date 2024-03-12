package com.project.mdyshop.controller;

import com.project.mdyshop.model.Shop;
import com.project.mdyshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/shop")
@PreAuthorize("hasRole('ADMIN')")
public class AdminShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("/")
    public ResponseEntity<List<Shop>> getAllShop() {
        List<Shop> shops = shopService.getAllShop();

        return new ResponseEntity<>(shops, HttpStatus.OK);
    }
}
