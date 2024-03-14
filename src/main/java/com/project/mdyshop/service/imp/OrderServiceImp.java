package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateOrderRequest;
import com.project.mdyshop.exception.CouponException;
import com.project.mdyshop.model.*;
import com.project.mdyshop.repository.*;
import com.project.mdyshop.service.CouponService;
import com.project.mdyshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CouponRepository couponRepository;
    @Override
    public Order createOrder(User user, CreateOrderRequest request)  throws CouponException {

        Cart cart = cartRepository.findCartByShopIdAndUserId(request.getShopId(), user.getId());
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem item: cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();

            orderItem.setSize(item.getSize());
            orderItem.setProduct(item.getProduct());
            orderItem.setPrice(item.getPrice());
            orderItem.setQuantity(item.getQuantity());

            OrderItem createOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createOrderItem);
            cartItemRepository.deleteById(item.getId());
        }
        cartRepository.delete(cart);

        if (request.getVoucher() != null) {
            Optional<Coupon> opt = couponRepository.findById(request.getVoucher().getId());
            if (opt.isPresent()) {
                Coupon coupon = opt.get();
                if (coupon.getQuantity() > 0) {
                    coupon.setQuantity(coupon.getQuantity() - 1);
                }
                else {
                    throw new CouponException("Coupon has expired");
                }
                couponRepository.save(coupon);
            }
        }

        if (request.getVoucherShop() != null) {
            Optional<Coupon> opt = couponRepository.findById(request.getVoucherShop().getId());
            if (opt.isPresent()) {
                Coupon coupon = opt.get();
                if (coupon.getQuantity() > 0) {
                    coupon.setQuantity(coupon.getQuantity() - 1);
                }
                else {
                    throw new CouponException("Coupon has expired");
                }
                couponRepository.save(coupon);
            }
        }

        Order order = new Order();

        order.setUser(user);
        order.setShopId(request.getShopId());
        order.setFirstName(request.getFirstName());
        order.setLastName(request.getLastName());
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setPhone(request.getPhone());
        order.setTotalPrice(cart.getTotalPrice());
        order.setTotalItems(cart.getTotalItems());
        order.setStatus("REQUEST");

        order.setCreateAt(LocalDateTime.now());
        order.setOrderItems(orderItems);
        order.setPayment(request.getPayment());

        if (request.getDiscountPrice() == 0) {
            order.setDiscountedPrice(cart.getTotalPrice());
            Order savedOrder = orderRepository.save(order);

            for (OrderItem item: orderItems) {
                item.setOrder(savedOrder);
                orderItemRepository.save(item);
            }
            return savedOrder;
        }
        else {
            order.setDiscountedPrice(request.getTotalPrice());
            Order savedOrder = orderRepository.save(order);

            for (OrderItem item: orderItems) {
                item.setOrder(savedOrder);
                orderItemRepository.save(item);
            }
            return savedOrder;
        }
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> getListUserOrders(Long userId) {
        return orderRepository.findOrderByUserId(userId);
    }

    @Override
    public List<Order> getShopOrder(Long shopId) {
        return orderRepository.findOrderByShopId(shopId);
    }

    @Override
    public Order updateOrderStatus(Long orderId, String status) {
        Optional<Order> opt = orderRepository.findById(orderId);
        if (opt.isPresent()) {
            Order order = opt.get();
            order.setStatus(status);
            if (order.getStatus().equals("SHIPPED")) {
                order.setDeliveryDate(LocalDateTime.now());
            }
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public Order getUserOrder(Long orderId) {
        Optional<Order> opt = orderRepository.findById(orderId);
        return opt.orElse(null);
    }

    @Override
    public List<Order> getUserOrderByStatus(Long shopId, String status) {
        return orderRepository.findUserOrderByStatus(shopId, status);
    }
}
