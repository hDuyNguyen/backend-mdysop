package com.project.mdyshop.dto.request;

import com.project.mdyshop.model.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String phone;

    private Coupon voucher;

    private Coupon voucherShop;

    private Long shopId;

    private long totalPrice;

    private String payment;

    private long discountPrice;
}

