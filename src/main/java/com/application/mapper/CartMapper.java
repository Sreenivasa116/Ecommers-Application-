package com.application.mapper;

import com.application.entities.CartEntity;
import com.application.entities.CartItemEntity;
import com.example.model.Cart;
import com.example.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    Cart toDto(CartEntity cartEntity);
    CartEntity toEntity(Cart cart);
    CartItem toDto(CartItemEntity cartItemEntity);
}
