package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CouponRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.model.Coupon;
import com.project.mdyshop.model.CouponStatus;
import com.project.mdyshop.repository.CouponRepository;
import com.project.mdyshop.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CouponServiceImp implements CouponService {

    @Autowired
    CouponRepository couponRepository;
    @Override
    public Coupon createCoupon(CouponRequest request, Long shopId) throws CouponException{
        Coupon coupon = new Coupon();

        coupon.setCode(request.getCode());
        coupon.setDescription(request.getDescription());
        coupon.setStatus("AVAILABLE");
        coupon.setNumber(request.getNumber());
        coupon.setDiscountType(request.getDiscountType());
        coupon.setCreatedAt(LocalDateTime.now());
        coupon.setQuantity(request.getQuantity());
        coupon.setMinPrice(request.getMinPrice());
        coupon.setTimeStart(LocalDateTime.parse(request.getTimeStart()));
        coupon.setTimeEnd(LocalDateTime.parse(request.getTimeEnd()));

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCouponAdmin(Long couponId, CouponRequest request) throws CouponException {
        Optional<Coupon> opt = couponRepository.findById(couponId);

        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            if (coupon.getCode().equals(request.getCode())) {
                throw new CouponException("Code not changed");
            }

            coupon.setCode(request.getCode());
            coupon.setDescription(request.getDescription());
            coupon.setNumber(request.getNumber());
            coupon.setDiscountType(request.getDiscountType());
            coupon.setQuantity(request.getQuantity());
            coupon.setMinPrice(request.getMinPrice());
            coupon.setTimeStart(LocalDateTime.parse(request.getTimeStart()));
            coupon.setTimeEnd(LocalDateTime.parse(request.getTimeEnd()));

            return couponRepository.save(coupon);
        }
        throw new CouponException("Coupon not found with ID: " + couponId);
    }

    @Override
    public void deleteCoupon(Long couponId) throws CouponException{
        Optional<Coupon> opt = couponRepository.findById(couponId);

        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            couponRepository.delete(coupon);
        }
        throw new CouponException("Coupon not found with ID: " + couponId);

    }

    @Override
    public int checkCoupon(Long couponId) throws CouponException {
        Optional<Coupon> opt = couponRepository.findById(couponId);

        if (opt.isPresent()) {
            Coupon coupon = opt.get();

            return coupon.getQuantity();
        }
        throw new CouponException("Coupon not found with ID: " + couponId);
    }

    @Override
    public List<Coupon> couponShop(Long shopId) {

        return couponRepository.couponShop(shopId);
    }

    @Override
    public List<Coupon> couponAdmin() {
        return couponRepository.couponAdmin();
    }

}
