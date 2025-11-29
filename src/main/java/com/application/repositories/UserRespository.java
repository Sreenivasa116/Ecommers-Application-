package com.application.repositories;

import com.application.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRespository extends JpaRepository<UserEntity,Integer> {

    Optional<UserEntity> findByEmail(String email);
//    Optional<UserEntity> findByIdPassword(Integer id ,String Password);
//    boolean saveUser(UserEntity user);
}
