package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Order;


import java.util.LinkedList;
import java.util.List;

public class CurrentOrders {
    private static List<Order> orders = new LinkedList<>();

    public static void addOrder(Order order){
        orders.add(order);
    }

    public static void removeOrder(Order order){
        orders.remove(order);
    }

    public static Order getOrder(int userid){
        for (Order order : orders) {
            if (order.getUserid() == userid){
                return order;
            }
        }
        return null;
    }

    public static int createNewOrderId(){
        if (orders.size()==0){
            return 1;
        }
        return orders.get(orders.size()-1).getId()+1;
    }
}
