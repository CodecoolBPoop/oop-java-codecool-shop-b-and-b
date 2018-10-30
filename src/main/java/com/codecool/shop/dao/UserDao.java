package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

public interface UserDao {

    void add(User user);

    User getUser(String email, String password);
}
