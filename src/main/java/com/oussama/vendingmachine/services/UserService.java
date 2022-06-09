package com.oussama.vendingmachine.services;

import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public User getUserById(String username) {
        if (userRepository.findById(username).isPresent()) {
            return userRepository.findById(username).get();
        }

        return null;

    }

    public boolean insertUser(User user) {
        if (!userRepository.findById(user.getUsername()).isPresent()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }else
            return false;

    }

    public boolean updateUser(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            userRepository.save(user);
            return true;
        }else
            return false;
    }

    public void deleteUserByUsername(String username) {
        User user = getUserById(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
       if(userRepository.findById(username).isEmpty()){
           throw new UsernameNotFoundException("user not found");
       }else{
            user=userRepository.findById(username).get();
       }
        Collection<SimpleGrantedAuthority> authoritys=new ArrayList<>();
       authoritys.add(new SimpleGrantedAuthority(user.getRole()));
       return new  org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authoritys  );
    }
}
