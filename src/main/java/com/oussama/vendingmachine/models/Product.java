package com.oussama.vendingmachine.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private long productId;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "amount_available", nullable = false)
    private int amountAvailable;

    @Column(name = "cost", nullable = false)
    private double cost;

    @OneToOne
    @JoinColumn(name = "seller_id")
    private User user;

}
