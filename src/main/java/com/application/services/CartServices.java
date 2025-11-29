package com.application.services;


import com.application.entities.*;
import com.application.mapper.CartMapper;
import com.application.repositories.CartItemRepository;
import com.application.repositories.CartRepository;
import com.application.repositories.ProductRepository;
import com.application.repositories.UserRespository;
import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.model.CartItemRequest;
import com.example.model.UpdateCartItemQuantityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CartServices {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRespository userRespository;
    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem addItemToCart(CartItemRequest cartItemRequest){

        Integer userId = cartItemRequest.getUserId();
        Optional <UserEntity>  userOptional = userRespository.findById(userId);
        UserEntity user = userOptional.get();
        CartEntity cart = user.getCart();
        if(cart == null) {
          cart = new CartEntity();
          cart.setCreatedAt(java.time.LocalDateTime.now());
          cart.setUser(user);
          user.setCart(cart);
        }
        Integer cartId = cart.getId();
        // CHECKING THE PRODUCT IS IN DATABASE

        ProductEntity product = productRepository.findById(cartItemRequest.getProductId()).orElseThrow(
              () ->    new RuntimeException("No product exists by Product Id : "+cartItemRequest.getProductId()));

        // CHECKING IF CART ITEM IS EXISTING IN THE USER CART
        CartItemEntity cartItem = cart.getCartItems() != null ?
                cart.getCartItems().stream()
                        .filter(item -> item.getId().equals(product.getId())).findFirst()
                        .orElseThrow(() -> new RuntimeException("No Cart Item exists"))
                : null ;

        if(cartItem != null)
            cartItem.setQuantity(cartItem.getQuantity()+ cartItemRequest.getQuantity());
        else{
            cartItem = new CartItemEntity();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(cartItemRequest.getQuantity());
           cart.getCartItems().add(cartItem);
        }
        cartRepository.save(cart);
      return cartMapper.toDto(cartItem);
    }

    public CartItem updateCartItemQuantity
            (Integer itemId,
             UpdateCartItemQuantityRequest updateCartItemQuantityRequest){

        Integer quantity = updateCartItemQuantityRequest.getQuantity();
        CartItemEntity item = cartItemRepository.findById(itemId).orElseThrow(
                () -> new RuntimeException("No Cart Item Exists on id"));
        if(quantity == null || quantity <= 0)
          throw new IllegalArgumentException("Send Proper Quantity of item");
        if(item.getProduct().getStockQuantity() < quantity)
            throw new RuntimeException("Insufficient Product Quantity");
        item.setQuantity(quantity);
        item = cartItemRepository.save(item);
        return cartMapper.toDto(item);
    }

    public Cart getCart(Integer userId){
        //CHECK IF CART EXISTS
       UserEntity user =  userRespository.findById(userId).orElseThrow(
                 ()-> new RuntimeException("User Does Not Exists By User Id "+userId));
       Integer cartId = user.getCart().getId();

        return  null;
    }

    public String removeCartItem(Integer itemId) {
        if (itemId == null || itemId < 1)
            throw  new IllegalArgumentException("Invalid cart item Id "+itemId);
        CartItemEntity cartItem = cartItemRepository.findById(itemId).orElseThrow(()->
                new RuntimeException("No cart item exists by ID"+itemId));
        cartItemRepository.delete(cartItem);
        return "Deleted cart item by id "+itemId;
    }

    public String clearCartByUserId(Integer userId){
        if( userId == null || userId <1)
            throw new IllegalArgumentException("Invalid UserId "+userId);

        CartEntity userCart = cartRepository.findByUserId(userId).orElseThrow(
                ()-> new RuntimeException("no cart exist by id "+userId));

        if(userCart.getCartItems() == null || userCart.getCartItems().isEmpty() ){
            return  "Nothing to cart";
        }
        cartItemRepository.deleteAll(userCart.getCartItems());

      return  "Cleared Cart successfully";
    }

    public Cart getCartByUserId(Integer userId){

        if(userId == null || userId <1) throw new IllegalArgumentException("Invalid user Id "+userId);

      CartEntity cart = cartRepository.findByUserId(userId).orElseThrow(
              () -> new RuntimeException("No Cart exixts by user Id "+userId) );

      Double total =   cart.getCartItems().stream().mapToDouble(cartItem -> cartItem.getProduct()
                            .getPrice()*cartItem.getQuantity()).sum();
      Cart cartResponse = cartMapper.toDto(cart);
      cartResponse.setTotalAmount(total);
      return cartResponse;
    }

}
