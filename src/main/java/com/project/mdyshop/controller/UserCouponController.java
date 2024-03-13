package com.project.mdyshop.controller;

import com.project.mdyshop.model.Coupon;
import com.project.mdyshop.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/coupon")
public class UserCouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("/couponShop/{shopId}")
    public ResponseEntity<List<Coupon>> getCouponShop(@PathVariable Long shopId) {
        List<Coupon> coupons = couponService.couponShop(shopId);

        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Coupon>> getCouponAdmin() {
        List<Coupon> coupons = couponService.getCouponAdmin();

        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }
}
