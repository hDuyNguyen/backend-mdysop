package com.project.mdyshop.controller;

import com.project.mdyshop.config.JwtTokenProvider;
import com.project.mdyshop.dto.request.CreateShopRequest;
import com.project.mdyshop.dto.request.UpdateShopRequest;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop")
@PreAuthorize("hasRole('SHOP')")
public class ShopController {
    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;

    @PostMapping("/create")
    public ResponseEntity<Shop> createShop(@RequestBody CreateShopRequest request, @RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);

        Shop shop = shopService.createShop(request, user);

        return new ResponseEntity<>(shop, HttpStatus.CREATED);
    }

    @PutMapping("/update/{shopId}")
    public ResponseEntity<Shop> updateShop(@RequestBody CreateShopRequest request, @PathVariable Long shopId, @RequestHeader("Authorization")String jwt) throws ShopException {
        Shop shop = shopService.updateShop(shopId, request);

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Shop> getShop(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);

        Shop shop = shopService.findShopByUser(user.getId());

        return new ResponseEntity<>(shop, HttpStatus.OK);
    }
}
