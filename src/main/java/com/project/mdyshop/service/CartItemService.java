package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.AddCartItemRequest;
import com.project.mdyshop.exception.CartException;
import com.project.mdyshop.exception.CartItemException;
import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.CartItem;
import com.project.mdyshop.model.User;

public interface CartItemService {

    CartItem addCartItem(AddCartItemRequest request, User user) throws CartException;

    void deleteCartItem(Long cartItemId) throws CartItemException;
}
