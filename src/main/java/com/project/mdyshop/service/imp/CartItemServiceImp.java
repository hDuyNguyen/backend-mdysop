package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.AddCartItemRequest;
import com.project.mdyshop.exception.CartException;
import com.project.mdyshop.exception.CartItemException;
import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.CartItem;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.CartItemRepository;
import com.project.mdyshop.repository.CartRepository;
import com.project.mdyshop.service.CartItemService;
import com.project.mdyshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartItemServiceImp implements CartItemService {

    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartService cartService;
    @Autowired
    CartItemRepository cartItemRepository;
    @Override
    public CartItem addCartItem(AddCartItemRequest request, User user) throws CartException {
        CartItem cartItem = new CartItem();

        cartItem.setQuantity(request.getQuantity());
        cartItem.setPrice(request.getPrice());
        cartItem.setSize(request.getSize());
        cartItem.setProduct(request.getProduct());

        Cart cart = cartRepository.findCartByShopIdAndUserId(request.getProduct().getId(), user.getId());

        if (cart != null) {
            cartItem.setCart(cart);
            cartService.updateCart(cartItem.getId(), cartItem.getPrice(), cart.getTotalItems());

            return cartItemRepository.save(cartItem);
        }
        else {
            Cart createCart = cartService.createCart(user, request.getProduct().getId());
            cartItem.setCart(createCart);

            createCart.setTotalItems(createCart.getTotalItems() + cartItem.getQuantity());
            createCart.setTotalPrice(createCart.getTotalPrice() + cartItem.getPrice());
            cartRepository.save(createCart);

            return cartItemRepository.save(cartItem);
        }
    }

    @Override
    public void deleteCartItem(Long cartItemId) throws CartItemException {
        Optional<CartItem> opt = cartItemRepository.findById(cartItemId);

        if (opt.isPresent()) {
            CartItem cartItem = opt.get();
            cartItemRepository.deleteById(cartItem.getId());
        }
        throw new CartItemException("Cart Item not found with ID: " + cartItemId);
    }
}
