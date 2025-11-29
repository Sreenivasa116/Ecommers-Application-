package com.application.controllers;


import com.application.exception.UserNotFoundException;
import com.application.services.UserService;


import com.example.api.UserApi;
import com.example.model.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;


import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserService userService;


    @Override
    public ResponseEntity<UserResponse> createUser
            (@Valid @RequestBody UserCreateRequest userCreateRequest) {
        UserResponse response = userService.createUser(userCreateRequest);
        if(response != null)
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


//
//    @Override
//    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
//        User user = userService.getUserByIdService(id);
//        return ResponseEntity.status(200).body(user);
//    }
//
//    @Override
//    public ResponseEntity<User> updateUserById(Integer id, UserUpdateRequest userUpdateRequest) {
//        User res = userService.updateUserByIdService(id,userUpdateRequest);
//        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(res);
//    }
//
//    @Override
//    public ResponseEntity<String> deleteUserById(Integer id) {
//        return ResponseEntity.ok(userService.deleteUserById(id));
//    }
//
//    @Override
//    public ResponseEntity<User> getUserByEmail(String email) {
//        User user = userService.getUserByEmail(email);
//        return ResponseEntity.status(200).body(user);
//    }
//
//    @Override
//    public ResponseEntity<List<User>> getAllUsers() {
//        List<User> list = userService.getAllUsers();
//        return ResponseEntity.status(200).body(list) ;
//    }
//
//    @Override
//    public ResponseEntity<String> toUpdatePassword
//            (Integer id, ToUpdatePasswordRequest toUpdatePasswordRequest) {
//        String s = userService.toUpdatePassword(id,toUpdatePasswordRequest);
//        if(s != null && s != "")
//            return ResponseEntity.status(HttpStatus.OK).body(s);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Update Pasword");
//    }

}
