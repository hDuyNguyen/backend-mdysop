package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateOrderRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.Order;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

public interface OrderService {

    Order createOrder(User user, CreateOrderRequest request) throws CouponException;
}
