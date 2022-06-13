package com.oussama.vendingmachine.services;

import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;
import com.oussama.vendingmachine.repositorys.ProductRepository;
import com.oussama.vendingmachine.repositorys.UserRepository;
import com.oussama.vendingmachine.request.BuyOrder;
import com.oussama.vendingmachine.request.BuyResponse;
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

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;
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
            if (user.getDeposit() != 0) return Constant.FORBIDDEN;
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return Constant.OK;
        } else
            return Constant.BAD_REQUEST;

    }

    public int updateUser(User user) {
        if (userRepository.findById(user.getUsername()).isPresent()) {
            userRepository.save(user);
            return Constant.OK;
        } else
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
        if (userRepository.findById(username).isEmpty()) {
            throw new UsernameNotFoundException("user not found");
        } else {
            user = userRepository.findById(username).get();
        }
        Collection<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        authoritys.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authoritys);
    }


    public int deposit(double amount) {
        if (Constant.allowedAmount.contains(amount)) {
            User user = userRepository.findById(CurrentUser.getCurrentLoggedUser()).get();
            user.setDeposit(user.getDeposit() + amount);
            userRepository.save(user);
            return Constant.OK;
        }
        return Constant.FORBIDDEN;
    }

    public int reset(){
        User user=userRepository.findById(CurrentUser.getCurrentLoggedUser()).get();
        user.setDeposit(0);
        userRepository.save(user);
        return Constant.OK;
    }

    public BuyResponse buy(BuyOrder order) {
        if (productRepository.findById(order.getProductId()).isEmpty()) return null;
        Product product = productRepository.findById(order.getProductId()).get();
        System.out.println("this the seller from buy function"+product.getUser().getUsername());
        User user = getUserById(CurrentUser.getCurrentLoggedUser());
        if (order.isValid(product.getAmountAvailable(), user.getDeposit(), product.getCost())) {
            double moneyLeft = user.getDeposit() - (product.getCost() * order.getAmountOfProduct());
            int quantityLeft = product.getAmountAvailable() - order.getAmountOfProduct();
            user.setDeposit(moneyLeft);
            product.setAmountAvailable(quantityLeft);
            userRepository.save(user);
            productRepository.save(product);
            product.setUser(null);
            BuyResponse buyResponse = new BuyResponse(product, (product.getCost() * order.getAmountOfProduct()));
            buyResponse.getChangeBack(moneyLeft);
            System.out.println(buyResponse.getChange());
            return buyResponse;
        }

        return null;
    }

}
