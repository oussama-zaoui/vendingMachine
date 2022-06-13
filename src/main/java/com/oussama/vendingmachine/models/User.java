package com.oussama.vendingmachine.models;

import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.data.annotation.Reference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "users")
public class User {


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



   public User(String username){
        this.username=username;
    }

    public User() {

    }

    public User(String username,String password,String role){
        this.username=username;
        this.password=password;
        this.role=role;
    }


    @Id
    @NotNull
    @NotEmpty
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    @NotNull
    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "deposit", nullable = false)
    private double deposit=0;
    @NotNull
    @NotEmpty
    @Column(name = "role", nullable = false)
    private String role;


}

