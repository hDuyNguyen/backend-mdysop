package com.project.mdyshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String description;

    private Long number;

    private String discountType;

    private String couponType;

    private int quantity;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime timeStart;

    private LocalDateTime timeEnd;

    private long minPrice;

    private Long shopId;

}
