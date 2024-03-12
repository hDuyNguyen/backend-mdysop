package com.project.mdyshop.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCouponRequest {

    private String description;

    private Long number;

    private String discountType;

    private String couponType;

    private int quantity;

    private String timeStart;

    private String timeEnd;

    private long minPrice;
}
