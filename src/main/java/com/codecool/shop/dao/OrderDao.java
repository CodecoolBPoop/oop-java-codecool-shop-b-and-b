package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

public interface OrderDao {

    void addOrder(Order order);

    void removeOrder(Order order);

    Order getOrder(int id);
}
