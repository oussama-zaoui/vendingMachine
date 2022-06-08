package com.oussama.vendingmachine.controllers;

import com.oussama.vendingmachine.exceptions.ResourceNotFoundException;
import com.oussama.vendingmachine.models.User;

import com.oussama.vendingmachine.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;


    @GetMapping("/{username}")
    public User getUser(@PathVariable String username) throws ResourceNotFoundException {
        User user = userService.getUserById(username);
        if (user == null) {
            throw new ResourceNotFoundException("use not found");
        }

        return user;
    }


    @PostMapping("/newUser")
    public void newUser(@RequestBody User user) {

        if (user != null) {
            userService.insertUser(user);
        }
    }

    @PatchMapping("/update")
    public void updateUser(@RequestBody User user) {
        if (user != null) {
            userService.updateUser(user);
        }
    }


    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }


}
