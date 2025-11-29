package com.application.repositories;

import com.application.entities.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItemEntity,Integer> {
    List<CartItemEntity> findByCartId(Integer cartId);
}
