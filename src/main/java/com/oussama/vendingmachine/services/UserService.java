package com.oussama.vendingmachine.services;

import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.UserRepository;
import com.oussama.vendingmachine.utils.Constant;
import com.oussama.vendingmachine.utils.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    public int insertUser(User user) {
        if (userRepository.findById(user.getUsername()).isEmpty()) {
            if (user.getDeposit()!=0) return Constant.FORBIDDEN;
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return Constant.OK;
        }else
            return Constant.BAD_REQUEST;

    }

    public int updateUser(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            userRepository.save(user);
            return Constant.OK;
        }else
            return Constant.NOT_FOUND;
    }

    public int deleteUserByUsername(String username) {
        User user = getUserById(username);
        if (user != null) {
            userRepository.delete(user);
            return Constant.OK;
        }
        return Constant.NOT_FOUND;
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


    public int deposit(double amount){
        System.out.println("this is the amount "+amount);
        ArrayList<Double> allowedAmount=new ArrayList<>(List.of(5.0,10.0,20.0,50.0,100.0));
        if (allowedAmount.contains(amount)){
            User user=userRepository.findById(CurrentUser.getCurrentLoggedUser()).get();
            user.setDeposit(user.getDeposit()+amount);
            userRepository.save(user);
            return Constant.OK;
        }
        return Constant.FORBIDDEN;
    }

}
