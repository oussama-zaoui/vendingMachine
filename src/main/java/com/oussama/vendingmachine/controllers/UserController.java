package com.oussama.vendingmachine.controllers;

import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.UserRepository;
import com.oussama.vendingmachine.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserRepository userRepository;


    @GetMapping("/:id")
    public User getUser(@RequestParam long id){
        return userRepository.getReferenceById(id);
    }







}
