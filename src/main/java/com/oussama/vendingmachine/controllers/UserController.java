package com.oussama.vendingmachine.controllers;


import com.oussama.vendingmachine.models.User;

import com.oussama.vendingmachine.services.UserService;

import com.oussama.vendingmachine.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    public UserService userService;


    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username)  {
        User user = userService.getUserById(username);
        if (user == null) {
           return  ResponseEntity.status(Constant.BAD_REQUEST).build();

        }

        return ResponseEntity.of(Optional.of(user));
    }


    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@RequestBody User user) {
        if (user != null && user.getUsername()!=null && !user.getUsername().isEmpty()) {
            return ResponseEntity.status(userService.insertUser(user)).build();
        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (user != null && user.getUsername()!=null && !user.getUsername().isEmpty()) {

            return ResponseEntity.status(userService.updateUser(user)).build();
        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }


    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
       return ResponseEntity.status(userService.deleteUserByUsername(username)).build();
    }


}
