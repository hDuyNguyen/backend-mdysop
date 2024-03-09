package com.project.mdyshop.service;

import com.project.mdyshop.model.Address;
import com.project.mdyshop.model.Order;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

public interface OrderService {

    Order createOrder(User user, Address shippingAddress, Long shopId);
}
