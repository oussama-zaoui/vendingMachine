package com.oussama.vendingmachine.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "seller",referencedColumnName = "username")
    private User user;
    @NotNull
    @NotEmpty
    @Column(name = "productName", nullable = false,unique = true)
    private String productName;
    @NotNull
    @NotEmpty
    @Column(name = "amountAvailable", nullable = false)
    private int amountAvailable;
    @NotNull
    @NotEmpty
    @Column(name = "cost", nullable = false)
    private double cost;





    public Product() {

    }

    public Product(User user){
        this.user=user;
    }

    public Product(String productName, int amountAvailable, double cost, User user) {
        this.productName = productName;
        this.amountAvailable = amountAvailable;
        this.cost = cost;
        this.user = user;
    }



    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(int amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
