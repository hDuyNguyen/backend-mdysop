package com.project.mdyshop.service.imp;

import com.project.mdyshop.model.*;
import com.project.mdyshop.repository.*;
import com.project.mdyshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order createOrder(User user, Address shippingAddress, Long shopId) {

        shippingAddress.setUser(user);
        Address address = addressRepository.save(shippingAddress);
        user.getAddresses().add(address);
        userRepository.save(user);

        Cart cart = cartRepository.findCartByShopIdAndUserId(shopId, user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem item: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setSize(item.getSize());
            orderItem.setProduct(item.getProduct());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());

            OrderItem createOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createOrderItem);
        }

        Order order = new Order();

        order.setOrderItems(orderItems);
        order.setUser(user);
        order.setTotalItems(cart.getTotalItems());
        order.setTotalPrice(cart.getTotalPrice());
        order.setCreateAt(LocalDateTime.now());
        order.setStatus(OrderStatus.REQUEST);
        Order savedOrder = orderRepository.save(order);

        for (OrderItem item: orderItems) {
            item.setOrder(savedOrder);
            orderItemRepository.save(item);
        }
        return savedOrder;
    }
}
