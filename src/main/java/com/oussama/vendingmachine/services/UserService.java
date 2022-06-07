package com.oussama.vendingmachine.services;

import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(long id){
        return userRepository.getReferenceById(id);
    }
}
