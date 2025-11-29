package com.application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@Table(name= "ecom_users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "username")
    private String name;
    @Column(name = "userpassword")
    private String password;
    @Column(name="role")
    private String role;
    @Column(name = "email")
    private String email;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "address")
    private String address;


// User Cart One to One
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartEntity cart;
//  User Order One to Many
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<OrderEntity> order;
}
