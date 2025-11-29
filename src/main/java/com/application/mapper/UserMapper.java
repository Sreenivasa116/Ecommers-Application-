package com.application.mapper;

import com.application.entities.UserEntity;
import com.example.model.User;
import com.example.model.UserCreateRequest;
import com.example.model.UserResponse;
import org.mapstruct.*;

import java.util.List;
import java.util.Optional;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "role",target = "role")
    @Mapping(source = "email",target = "email")
    @Mapping(target = "createdDate",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    @Mapping(source = "address",target="address")
    UserEntity toUserEntity(UserCreateRequest user);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "role",target = "role")
    @Mapping(source = "email",target = "email")
    @Mapping(target = "createdDate",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    @Mapping(source = "address",target="address")
    UserEntity toUserEntity(UserResponse user);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "role",target = "role")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "createdDate",target = "createdDate")
    @Mapping(source = "updatedAt",target = "updatedAt")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    UserResponse toDtoResponse(UserEntity userEntity);
    List<UserResponse> toDtoListResponse (List<UserEntity> userEntities);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "role",target = "role")
    @Mapping(source = "email",target = "email")
    @Mapping(target = "createdDate",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    @Mapping(source = "address",target="address")
    UserEntity toUserEntity(User user);

    @Mapping(source = "id",target = "id")
    @Mapping(source = "name",target = "name")
    @Mapping(source = "password",target = "password")
    @Mapping(source = "role",target = "role")
    @Mapping(source = "email",target = "email")
    @Mapping(source = "createdDate",target = "createdDate")
    @Mapping(source = "updatedAt",target = "updatedAt")
    @Mapping(source = "phoneNumber",target = "phoneNumber")
    User toDto(UserEntity userEntity);
    List<User> toDtoList (List<UserEntity> userEntities);


//    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
//    @Mapping(source = "name",target = "name")
//    @Mapping(source = "password",target = "password")
//    @Mapping(source = "email",target = "email")
//    @Mapping(source = "phoneNumber",target = "phoneNumber")
//    @Mapping(target = "updatedAt",expression = "java(java.time.LocalDateTime.now())")
//    UserEntity toEntityForUpdation (User user, @MappingTarget UserEntity userEntity);

}
