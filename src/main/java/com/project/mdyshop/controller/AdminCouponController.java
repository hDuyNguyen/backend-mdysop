package com.project.mdyshop.controller;

import com.project.mdyshop.dto.response.ApiResponse;
import com.project.mdyshop.model.Coupon;
import com.project.mdyshop.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/couponShop")
@PreAuthorize("hasRole('ADMIN')")
public class AdminCouponController {

    @Autowired
    CouponService couponService;

    @GetMapping("/")
    public ResponseEntity<List<Coupon>> getAllCoupon() {
        List<Coupon> coupons = couponService.getCouponAdmin();

        return new ResponseEntity<>(coupons, HttpStatus.OK);
    }

    @PutMapping("/confirm/accept/{couponId}")
    public ResponseEntity<ApiResponse> confirmCoupon(@PathVariable Long couponId) {
        couponService.confirmCoupon(couponId);
        ApiResponse response = new ApiResponse();

        response.setMessage("Confirm Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/confirm/denied/{couponId}")
    public ResponseEntity<ApiResponse> deniedCoupon(@PathVariable Long couponId) {
        couponService.confirmCoupon(couponId);
        ApiResponse response = new ApiResponse();

        response.setMessage("Deny Successfully");
        response.setStatus(true);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
