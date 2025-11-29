package com.application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ecom_products")
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="product_id")
    private Integer id;

    @Column(name="product_name")
    private String name ;
    @Column(name ="product_description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "created_date")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "stock_quantity")
    private Integer stockQuantity;


    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderList;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItemList;

}
