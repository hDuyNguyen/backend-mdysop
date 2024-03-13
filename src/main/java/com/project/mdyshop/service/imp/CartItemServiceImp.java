package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.AddCartItemRequest;
import com.project.mdyshop.exception.CartException;
import com.project.mdyshop.exception.CartItemException;
import com.project.mdyshop.exception.ProductException;
import com.project.mdyshop.model.Cart;
import com.project.mdyshop.model.CartItem;
import com.project.mdyshop.model.Product;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.CartItemRepository;
import com.project.mdyshop.repository.CartRepository;
import com.project.mdyshop.repository.ProductRepository;
import com.project.mdyshop.service.CartItemService;
import com.project.mdyshop.service.CartService;
import com.project.mdyshop.service.ProductService;
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
    @Autowired
    ProductRepository productRepository;
    @Override
    public CartItem addCartItem(AddCartItemRequest request, User user) throws CartException {
        CartItem cartItem = new CartItem();

        Optional<Product> opt = productRepository.findById(request.getProductId());
        if (opt.isPresent()) {
            Product product = opt.get();

            Cart cart = cartRepository.findCartByShopIdAndUserId(product.getShop().getId(), user.getId());

            if (cart != null) {
                CartItem item = cartItemRepository.findByProductIdAndCartIdAndSize(product.getId(), cart.getId(), request.getSize());

                if (item != null) {
                    item.setQuantity(item.getQuantity() + request.getQuantity());
                    item.setPrice(item.getPrice() + request.getPrice());

                    cart.setTotalItems(cart.getTotalItems() + request.getQuantity());
                    cart.setTotalPrice(cart.getTotalPrice() + request.getPrice());

                    cartRepository.save(cart);
                    return cartItemRepository.save(item);
                }
                else  {
                    cartItem.setProduct(product);
                    cartItem.setSize(request.getSize());
                    cartItem.setPrice(request.getPrice());
                    cartItem.setQuantity(request.getQuantity());
                    cartItem.setCart(cart);

                    cart.setTotalItems(cart.getTotalItems() + request.getQuantity());
                    cart.setTotalPrice(cart.getTotalPrice() + request.getPrice());

                    cartRepository.save(cart);
                    return cartItemRepository.save(cartItem);
                }
            }
            else {
                Cart createCart = cartService.createCart(user, product.getShop().getId());

                cartItem.setProduct(product);
                cartItem.setCart(createCart);
                cartItem.setQuantity(request.getQuantity());
                cartItem.setPrice(request.getPrice());
                cartItem.setSize(request.getSize());

                createCart.setTotalPrice(createCart.getTotalPrice() + request.getPrice());
                createCart.setTotalItems(createCart.getTotalItems() + request.getQuantity());

                cartRepository.save(createCart);
                return cartItemRepository.save(cartItem);
            }
        }
        return null;
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
