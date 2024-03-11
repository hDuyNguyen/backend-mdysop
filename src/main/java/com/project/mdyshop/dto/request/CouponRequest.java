package com.project.mdyshop.dto.request;

import com.project.mdyshop.model.CouponStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponRequest {

    private String code;
    private String description;
    private Long number;
    private String discountType;
    private int quantity;
    private String timeStart;
    private String timeEnd;
    private long minPrice;

}
