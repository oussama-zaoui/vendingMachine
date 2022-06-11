package com.oussama.vendingmachine.request;

import com.oussama.vendingmachine.models.Product;
import com.oussama.vendingmachine.utils.Constant;

import java.util.ArrayList;

public class BuyResponse {

    private Product product;

    private double totalSpent;

    private ArrayList<Double> change;

    public BuyResponse(Product product, double totalSpent) {
        this.product = product;
        this.totalSpent = totalSpent;
        this.change=new ArrayList<>();

    }
    public BuyResponse(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public ArrayList<Double> getChange() {
        return change;
    }

    public void setChange(ArrayList<Double> change) {
        this.change = change;
    }

    public void getChangeBack(double moneyLeft){
        for(int i = Constant.allowedAmount.size()-1; i>=0; i--){
            while(moneyLeft>=Constant.allowedAmount.get(i)){
                this.change.add(Constant.allowedAmount.get(i));
                moneyLeft-=Constant.allowedAmount.get(i);
            }
        }
    }
}
