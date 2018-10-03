package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.AllCarts;

import java.util.LinkedList;
import java.util.List;

public class Cart {
    private List<Product> items;
    private int id;

    public int getId() {
        return id;
    }

    public Cart() {
        this.id = AllCarts.createNewCartId();
        this.items = new LinkedList<>();
        AllCarts.addCart(this);
    }

    public void addItem(Product product){
        items.add(product);
    }

    public void remove(Product product){
        items.remove(product);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                ", id=" + id +
                '}';
    }
}
