package com.oussama.vendingmachine.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "deposit", nullable = false)
    private double deposit;
    @Column(name = "role",nullable = false)
    private String role;



}

