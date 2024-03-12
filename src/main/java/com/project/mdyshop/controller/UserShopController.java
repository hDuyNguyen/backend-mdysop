package com.project.mdyshop.controller;

import com.project.mdyshop.model.Shop;
import com.project.mdyshop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/shop")
public class UserShopController {

    @Autowired
    ShopService shopService;

    @GetMapping("/{shopId}")
    public ResponseEntity<Shop> getShop(@PathVariable Long shopId) {
        Shop shop = shopService.findById(shopId);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
}
