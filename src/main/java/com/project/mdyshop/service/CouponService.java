package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CouponRequest;
import com.project.mdyshop.dto.request.UpdateCouponRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.model.Coupon;

import java.util.List;

public interface CouponService {

    Coupon createCoupon(CouponRequest request) throws CouponException;
    Coupon createCouponShop(CouponRequest request, Long shopId) throws CouponException;

    Coupon updateCoupon(Long couponId, UpdateCouponRequest req) throws CouponException;

    Coupon updateCouponAdmin(Long couponId, CouponRequest request) throws CouponException;

    Coupon updateStatusCoupon(Long couponId);

    void deleteCoupon(Long couponId) throws CouponException;

    Coupon checkCoupon(Long couponId) throws CouponException;

    List<Coupon> couponShop(Long shopId);

    List<Coupon> getCouponAdmin();

    void confirmCoupon(Long couponId);

    void deniedCoupon(Long couponId);
}
