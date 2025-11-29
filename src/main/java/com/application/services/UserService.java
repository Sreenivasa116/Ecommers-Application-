package com.application.services;


import com.application.entities.UserEntity;
import com.application.exception.UserNotFoundException;
import com.application.mapper.UserMapper;
import com.application.repositories.UserRespository;
import com.example.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.*;
import java.util.function.Function;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRespository userRespository;

    public UserResponse createUser(@Valid @RequestBody UserCreateRequest UserCreateRequest){
        try {
            UserEntity userEntity = userMapper.toUserEntity(UserCreateRequest);
            userEntity.setRole("CUSTOMER");
            userEntity  = userRespository.save(userEntity);
            UserResponse userResponse = userMapper.toDtoResponse(userEntity);
           return userResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


//
//    public  User getUserByIdService(Integer id){
//         UserEntity ue = userRespository.findById(id).
//                 orElseThrow(() -> {throw  new UserNotFoundException("user not found");});
//         User user = userMapper.toDto(ue);
//         return  user;
//    }
//
//    public User updateUserByIdService(Integer id, UserUpdateRequest userUpdateRequest){
//
//        UserEntity userEntity;
//
//        try{
//
//           Optional<UserEntity> oue = userRespository.findById(id);
//           userEntity = oue.get();
//           if(userUpdateRequest.getName() != null)
//               userEntity.setName(userUpdateRequest.getName());
//           if(userUpdateRequest.getAddress() != null)
//               userEntity.setAddress(userUpdateRequest.getAddress());
//           if(userUpdateRequest.getEmail() != null)
//               userEntity.setEmail(userUpdateRequest.getEmail());
//           if(userUpdateRequest.getPassword() != null)
//               userEntity.setPassword(userUpdateRequest.getPassword());
//           if(userUpdateRequest.getPhoneNumber() != null)
//               userEntity.setPhoneNumber(userUpdateRequest.getPhoneNumber());
//
//           userEntity.setUpdatedAt(java.time.LocalDateTime.now());
//           userEntity = userRespository.save(userEntity);
//
//        } catch (RuntimeException e) {
//            throw new RuntimeException(e);
//        }
//        return userMapper.toDto(userEntity);
//    }
//
//    public String deleteUserById(Integer id){
//
//        userRespository.deleteById(id);
//        return "Deleted User By Id";
//    }
//
//    public  User getUserByEmail(String email){
//        UserEntity userEntity = userRespository.findByEmail(email).orElseThrow(
//                () -> {throw new UserNotFoundException("NO user by email id");});
//        return userMapper.toDto(userEntity);
//    }
//
//    public  List<User> getAllUsers(){
//
//        List<User> list = userMapper.toDtoList(userRespository.findAll());
//        if(list == null)
//            throw new NullPointerException("No Elements In DataBase");
//        return list;
//    }
//
//    public String toUpdatePassword(Integer id, ToUpdatePasswordRequest toUpdatePasswordRequest){
//
//        UserEntity userEntity =userRespository.findByIdPassword(id,toUpdatePasswordRequest.getOldPassword())
//                .orElseThrow(()-> {throw new UserNotFoundException("No user found by user id");});
//        userEntity.setPassword(toUpdatePasswordRequest.getNewPassword());
//        if(userRespository.saveUser(userEntity)){
//            return "User Password Updated Succesfully";
//        }
//      return "Failed to Update User Password";
//    }

}
