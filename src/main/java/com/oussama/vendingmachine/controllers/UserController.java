package com.oussama.vendingmachine.controllers;


import com.oussama.vendingmachine.models.User;

import com.oussama.vendingmachine.services.UserService;

import com.oussama.vendingmachine.request.BuyOrder;
import com.oussama.vendingmachine.request.BuyResponse;
import com.oussama.vendingmachine.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = userService.getUserById(username);
        if (user == null) {
            return ResponseEntity.status(Constant.BAD_REQUEST).build();

        }

        return ResponseEntity.of(Optional.of(user));
    }


    @PostMapping("/newUser")
    public ResponseEntity<?> newUser(@RequestBody User user) {
        if (user != null && user.getUsername() != null && !user.getUsername().isEmpty()) {
            return ResponseEntity.status(userService.insertUser(user)).build();
        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (user != null && user.getUsername() != null && !user.getUsername().isEmpty()) {

            return ResponseEntity.status(userService.updateUser(user)).build();
        }
        return ResponseEntity.status(Constant.BAD_REQUEST).build();
    }

    @PatchMapping("/deposit/{amount}")
    public ResponseEntity<?> deposit(@PathVariable double amount) {
        if (userService.deposit(amount) == Constant.OK) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(Constant.FORBIDDEN).body("amount should only be one of those (5, 10, 20, 50, 100)");
    }

    @PatchMapping("/reset")
    public ResponseEntity<?> reset(){
        return ResponseEntity.status(userService.reset()).build();
    }

    @PostMapping("/buy")
    public ResponseEntity<BuyResponse> buy(@RequestBody BuyOrder buyOrder) {
        if (buyOrder == null || buyOrder.getAmountOfProduct() == 0 || buyOrder.getProductId() == 0) {
            return ResponseEntity.status(Constant.BAD_REQUEST).build();
        }else{
            BuyResponse buyResponse = userService.buy(buyOrder);
            if(buyResponse!=null)  return ResponseEntity.of(Optional.of(buyResponse));
        }

        return ResponseEntity.status(Constant.FORBIDDEN).build();
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        return ResponseEntity.status(userService.deleteUserByUsername(username)).build();
    }


}
