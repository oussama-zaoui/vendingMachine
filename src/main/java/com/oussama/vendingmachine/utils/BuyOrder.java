package com.oussama.vendingmachine.utils;

import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.models.User;

public class BuyOrder {

    private long productId;
    private int amountOfProduct;

    public BuyOrder(){

    }

    public int getAmountOfProduct() {
        return amountOfProduct;
    }

    public long getProductId() {
        return productId;
    }

    public void setAmountOfProduct(int amountOfProduct) {
        this.amountOfProduct = amountOfProduct;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public boolean isValid(int amountAvailable,double deposit,double cost){
        /** check if the quantity is available**/
        System.out.println("this is is valid function "+amountAvailable);
        if (this.getAmountOfProduct()<=amountAvailable){
            /**check if the money is enough for the transaction**/
            System.out.println("after if with amount of product" +this.amountOfProduct);
            System.out.println(" and this is the product cost "+ cost);
           double chekla=cost * this.amountOfProduct;
            System.out.println("this is value of chekla "+chekla);
            if(deposit>=chekla){
                System.out.println("this is is valid function wixth user deposit "+deposit);
                return true;
            }
        }
        return false;
    }


}
