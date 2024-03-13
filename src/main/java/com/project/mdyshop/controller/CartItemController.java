package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.AddCartItemRequest;
import com.project.mdyshop.dto.response.ApiResponse;
import com.project.mdyshop.exception.CartException;
import com.project.mdyshop.exception.CartItemException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.CartItem;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.CartItemService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/cart_items")
@PreAuthorize("hasRole('USER')")
public class CartItemController {

    @Autowired
    UserService userService;
    @Autowired
    CartItemService cartItemService;

    @PostMapping("/create")
    public ResponseEntity<CartItem> addItemToCart(@RequestHeader("Authorization")String jwt,
                                                  @RequestBody AddCartItemRequest request) throws UserException, CartException {
        User user = userService.findUserByToken(jwt);
        CartItem cartItem = cartItemService.addCartItem(request, user);

        return new ResponseEntity<>(cartItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable Long cartItemId) throws CartItemException {
        cartItemService.deleteCartItem(cartItemId);

        ApiResponse response = new ApiResponse();

        response.setMessage("Delete Item successfully");
        response.setStatus(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
