package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.CurrentOrders;

import java.util.LinkedList;
import java.util.List;

public class Order {
    private List<LineItem> items;
    private int id;
    private double totatlPrice;
    private int totalItems;

    public int getId() {
        return id;
    }

    public Order() {
        this.id = CurrentOrders.createNewOrderId();
        this.items = new LinkedList<>();
        this.totalItems = 0;
        this.totatlPrice = 0;
        CurrentOrders.addOrder(this);
    }

    public void addItem(Product product){
        LineItem item = new LineItem(product);
        for (LineItem currentItem:items){
            if (currentItem.name == item.name){
                currentItem.increaseQuantity();
                return;
            }
        }
        items.add(item);
        this.totatlPrice += item.getDefaultPrice();
        this.totalItems += 1;
    }

    public void remove(Product product){
        items.remove(product);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public double getTotatlPrice() {
        return totatlPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "items=" + items +
                ", OrderId=" + id +
                '}';
    }
}
