package com.codecool.shop.model;

import java.util.Currency;

public class LineItem extends BaseModel {

    private int quantity;
    private float defaultPrice;
    private Currency defaultCurrency;
    private float totalPrice;

    public LineItem(Product product){
        super(product.name);
        this.defaultCurrency=product.getDefaultCurrency();
        this.defaultPrice = product.getDefaultPrice();
        this.quantity = 1;
        totalPrice = product.getDefaultPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void increaseQuantity(){
        this.quantity +=1;
        totalPrice+= this.defaultPrice;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "quantity=" + quantity +
                ", defaultPrice=" + defaultPrice +
                ", defaultCurrency=" + defaultCurrency +
                ", totalPrice=" + totalPrice +
                ", name='" + name + '\'' +
                '}';
    }
}
