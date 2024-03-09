package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CouponRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.model.Coupon;

import java.util.List;

public interface CouponService {

    Coupon createCoupon(CouponRequest request);

    Coupon updateCoupon(Long couponId, CouponRequest request) throws CouponException;

    void deleteCoupon(Long couponId) throws CouponException;

    int checkCoupon(Long couponId) throws CouponException;

    List<Coupon> getAllCoupon();
}
