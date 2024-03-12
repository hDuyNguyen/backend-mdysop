package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CouponRequest;
import com.project.mdyshop.dto.request.UpdateCouponRequest;
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
    public Coupon createCoupon(CouponRequest request) throws CouponException {
        Coupon coupon = new Coupon();

        coupon.setCode(request.getCode());
        coupon.setDescription(request.getDescription());
        coupon.setNumber(request.getNumber());
        coupon.setDiscountType(request.getDiscountType());
        coupon.setCouponType(request.getCouponType());
        coupon.setQuantity(request.getQuantity());
        coupon.setTimeStart(LocalDateTime.parse(request.getTimeStart()));
        coupon.setTimeEnd(LocalDateTime.parse(request.getTimeEnd()));
        coupon.setMinPrice(request.getMinPrice());
        coupon.setStatus("AVAILABLE");
        coupon.setCreatedAt(LocalDateTime.now());

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon createCouponShop(CouponRequest request, Long shopId) throws CouponException{
        Coupon coupon = new Coupon();

        coupon.setCode(request.getCode());
        coupon.setDescription(request.getDescription());
        coupon.setStatus("REQUEST");
        coupon.setNumber(request.getNumber());
        coupon.setDiscountType(request.getDiscountType());
        coupon.setCouponType(request.getCouponType());
        coupon.setCreatedAt(LocalDateTime.now());
        coupon.setQuantity(request.getQuantity());
        coupon.setMinPrice(request.getMinPrice());
        coupon.setTimeStart(LocalDateTime.parse(request.getTimeStart()));
        coupon.setTimeEnd(LocalDateTime.parse(request.getTimeEnd()));
        coupon.setShopId(shopId);

        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Long couponId, UpdateCouponRequest request) throws CouponException {
        Optional<Coupon> opt = couponRepository.findById(couponId);
        if (opt.isPresent()) {
            Coupon coupon = opt.get();

            coupon.setDescription(request.getDescription());
            coupon.setCouponType(request.getCouponType());
            coupon.setDiscountType(request.getDiscountType());
            coupon.setNumber(request.getNumber());
            coupon.setQuantity(request.getQuantity());
            coupon.setMinPrice(request.getMinPrice());
            coupon.setTimeStart(LocalDateTime.parse(request.getTimeStart()));
            coupon.setTimeEnd(LocalDateTime.parse(request.getTimeEnd()));
            coupon.setStatus("REQUEST");

            return couponRepository.save(coupon);
        }
        throw new CouponException("Coupon not found with ID:" + couponId);
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
    public Coupon updateStatusCoupon(Long couponId) {
        Optional<Coupon> opt = couponRepository.findById(couponId);
        if (opt.isPresent()) {
            Coupon coupon = opt.get();

            if (coupon.getStatus().equals("AVAILABLE")) {
                coupon.setStatus("HIDE");
            }
            else {
                coupon.setStatus("AVAILABLE");
            }
            return couponRepository.save(coupon);
        }
        return null;
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
    public Coupon checkCoupon(Long couponId) throws CouponException {
        Optional<Coupon> opt = couponRepository.findById(couponId);

        if (opt.isPresent()) {
            Coupon coupon = opt.get();

            if (coupon.getQuantity() <= 0) {
                coupon.setStatus("SOLD");
            }
            else {
                coupon.setQuantity(coupon.getQuantity() - 1);
            }
            return couponRepository.save(coupon);
        }
        throw new CouponException("Coupon not found with ID: " + couponId);
    }

    @Override
    public List<Coupon> couponShop(Long shopId) {

        return couponRepository.couponShop(shopId);
    }

    @Override
    public List<Coupon> getCouponAdmin() {
        return couponRepository.listCouponAdmin();
    }

    @Override
    public void confirmCoupon(Long couponId) {
        Optional<Coupon> opt = couponRepository.findById(couponId);

        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            coupon.setStatus("AVAILABLE");

            couponRepository.save(coupon);
        }
    }

    @Override
    public void deniedCoupon(Long couponId) {
        Optional<Coupon> opt = couponRepository.findById(couponId);

        if (opt.isPresent()) {
            Coupon coupon = opt.get();
            coupon.setStatus("DENY");

            couponRepository.save(coupon);
        }
    }

}
