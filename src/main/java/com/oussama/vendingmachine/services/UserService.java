package com.oussama.vendingmachine.services;

import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserById(String username) {
        if (userRepository.findById(username).isPresent()) {
            return userRepository.findById(username).get();
        }

        return null;

    }

    public void insertUser(User user) {
        if (!userRepository.findById(user.getUsername()).isPresent()) {
            userRepository.save(user);
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserByUsername(String username) {
        User user = getUserById(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }


}
