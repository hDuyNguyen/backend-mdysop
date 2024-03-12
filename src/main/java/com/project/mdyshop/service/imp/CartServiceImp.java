package com.project.mdyshop.service.imp;

import com.project.mdyshop.exception.CartException;
import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.CartItem;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.CartRepository;
import com.project.mdyshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    CartRepository cartRepository;
    @Override
    public Cart createCart(User user, Long shopId) {
        Cart cart = new Cart();

        cart.setUser(user);
        cart.setShopId(shopId);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateCart(Long cartId, Long totalPrice, Long totalItems) throws CartException {
        Optional<Cart> opt = cartRepository.findById(cartId);

        if (opt.isPresent()) {
            Cart cart = opt.get();

            cart.setTotalItems(cart.getTotalItems() + totalItems);
            cart.setTotalPrice(cart.getTotalPrice() + totalPrice);

            return cartRepository.save(cart);
        }
        throw new CartException("Cart not found with ID: " + cartId);
    }

    @Override
    public Cart findCartByShopAndUser(Long shopId, Long userId) {
        Cart cart = cartRepository.findCartByShopIdAndUserId(shopId, userId);

        long totalItem = 0;
        long totalPrice = 0;
        long totalDiscountPrice = 0;

        for (CartItem cartItem: cart.getCartItems()) {
            totalItem = totalItem + cartItem.getQuantity();
            totalPrice = totalPrice + cart.getTotalPrice();
        }
        cart.setTotalItems(totalItem);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }


}
