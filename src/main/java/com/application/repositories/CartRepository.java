package com.application.repositories;

import com.application.entities.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity,Integer> {

    Optional<CartEntity> findById(Integer userId);
    Optional<CartEntity> findByUserId(Integer userId);
}
