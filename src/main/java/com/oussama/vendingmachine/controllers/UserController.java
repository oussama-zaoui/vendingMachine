package com.oussama.vendingmachine.controllers;

import com.oussama.vendingmachine.exceptions.ResourceNotFoundException;
import com.oussama.vendingmachine.models.User;

import com.oussama.vendingmachine.services.UserService;

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
    public ResponseEntity<User> getUser(@PathVariable String username) throws ResourceNotFoundException {
        User user = userService.getUserById(username);
        if (user == null) {
            ResponseEntity.notFound();
            throw new ResourceNotFoundException("use not found");
        }

        return ResponseEntity.of(Optional.of(user));
    }


    @PostMapping("/newUser")
    public ResponseEntity<String> newUser(@RequestBody User user) {

        if (user != null) {
           if(userService.insertUser(user)){
               return new ResponseEntity<String>("user created succefully", HttpStatus.OK);
           }

        }
        return new ResponseEntity<String>("user already exist", HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (user != null) {
            if(userService.updateUser(user)){
                return new ResponseEntity<String>("user updated succefully",HttpStatus.OK);
            }

        }
        return new ResponseEntity<String>("user not found",HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/delete/{username}")
    public void deleteUser(@PathVariable String username) {
        userService.deleteUserByUsername(username);
    }


}
