package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateOrderRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.Order;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

import java.util.List;

public interface OrderService {

    Order createOrder(User user, CreateOrderRequest request) throws CouponException;

    List<Order> getAllOrder();

    List<Order> getListUserOrders(Long userId);

    List<Order> getShopOrder(Long shopId);

    Order updateOrderStatus(Long orderId, String status);

    Order getUserOrder(Long orderId);

    List<Order> getUserOrderByStatus(Long shopId, String status);
}
