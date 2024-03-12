package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CouponRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Coupon;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.CouponService;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/coupon")
@PreAuthorize("hasRole('SHOP')")
public class CouponShopController {

    @Autowired
    UserService userService;
    @Autowired
    ShopService shopService;
    @Autowired
    CouponService couponService;

    @PostMapping("/create")
    public ResponseEntity<Coupon> createCoupon(@RequestHeader("Authorization")String jwt,
                                               @RequestBody CouponRequest request)
        throws UserException, CouponException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        Coupon coupon = couponService.createCouponShop(request, shop.getId());

        return new ResponseEntity<>(coupon, HttpStatus.CREATED);
    }

    @PutMapping("/update/{couponId}")
    public ResponseEntity<Coupon> updateCoupon(@PathVariable Long couponId,
                                               @RequestBody CouponRequest request)
        throws CouponException {
        Coupon coupon = couponService.updateCouponAdmin(couponId, request);

        return new ResponseEntity<>(coupon, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Coupon>> getCoupons(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopService.findShopByUser(user.getId());

        List<Coupon> coupons = couponService.couponShop(shop.getId());

        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}
