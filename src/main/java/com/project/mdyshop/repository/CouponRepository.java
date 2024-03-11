package com.project.mdyshop.repository;

import com.project.mdyshop.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select c from Coupon c where c.code = :code")
    boolean findCouponByCode(@Param("code") String code);

    @Query("select c from Coupon c where c.shopId = :shopId")
    List<Coupon> couponShop(@Param("shopId")Long shopId);

    @Query("select c from Coupon c where c.shopId = null ")
    List<Coupon> couponAdmin();

}
