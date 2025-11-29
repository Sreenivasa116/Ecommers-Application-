package com.application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "ecom_cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="cart_id")
    private Integer id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // USer and cart one to one


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // DB relation cart to cart item One to Many

    @OneToMany(mappedBy="cart",cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItems ;
}
