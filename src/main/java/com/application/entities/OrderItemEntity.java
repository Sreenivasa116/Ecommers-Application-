package com.application.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ecom_order_items")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "price")
    private Double price;
    @Column(name = "total")
    private Double total;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
}

