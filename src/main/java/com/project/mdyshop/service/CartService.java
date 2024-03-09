package com.project.mdyshop.service;

import com.project.mdyshop.exception.CartException;
import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

public interface CartService {

    Cart createCart(User user, Long shopId);

    Cart updateCart(Long cartId, Long totalPrice, Long totalItems) throws CartException;

    Cart findCartByShopAndUser(Long shopId, Long userId);
}
