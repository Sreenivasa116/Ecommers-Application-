package com.application.controllers;

import com.application.services.CartServices;
import com.example.api.CartApi;
import com.example.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController implements CartApi {
    @Autowired
    private CartServices cartServices;

    @Override
    public ResponseEntity<CartItem> addItemToCart(CartItemRequest cartItemRequest) {
        CartItem ci = cartServices.addItemToCart(cartItemRequest);
        return ResponseEntity.ok(ci);
    }

    @Override
    public ResponseEntity<CartItem> updateCartItemQuantity
            (Integer itemId,
             UpdateCartItemQuantityRequest updateCartItemQuantityRequest) {

        CartItem cartItem = cartServices.updateCartItemQuantity(itemId, updateCartItemQuantityRequest);
        return ResponseEntity.ok(cartItem);
    }

    @Override
    public ResponseEntity<String> removeCartItem(Integer itemId) {
        return ResponseEntity.ok(cartServices.removeCartItem(itemId));
    }

    @Override
    public ResponseEntity<String> clearCartByUserId(Integer userId) {
        String result = cartServices.clearCartByUserId(userId);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<Cart> getCartByUserId(Integer userId) {
        Cart cart = cartServices.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }
    
}
