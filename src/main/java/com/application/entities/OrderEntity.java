package com.application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ecom_orders")
@Data
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orders_id")
    private Integer id;



    @Column(name = "ordered_date_time")
    private LocalDateTime orderDate;

    @Column(name = "order_total_amount")
    private  Double totalAmount;

    @Column(name = "status")
    private String status;


    @ManyToOne
    @JoinColumn(name="user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItemEntityList;


//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
//    private List<OrderItemEntity> items;
}
