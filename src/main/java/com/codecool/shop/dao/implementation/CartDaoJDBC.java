package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CartDaoJDBC extends JdbcBase {

    public Order getCart(int userid){
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carts WHERE user_id=?");
            preparedStatement.setInt(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order cart = new Order(resultSet.getInt("user_id"));
                cart.setId(resultSet.getInt("id"));
                cart.setItems(getLineItems(cart.getId()));
                connection.close();
                return cart;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveCart(Order cart) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carts WHERE id=?");
            preparedStatement.setInt(1,cart.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet != null){
                    PreparedStatement preparedStatement2= connection.prepareStatement("DELETE FROM carts WHERE id=?");
                    preparedStatement2.setInt(1, cart.getId());
                    preparedStatement2.execute();
                }
                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO carts (user_id, total_items, date,id) VALUES (?,?,?,?,?)");
                preparedStatement1.setInt(1,cart.getUserid());
                preparedStatement1.setDouble(2,cart.getTotalPrice());
                preparedStatement1.setInt(3,cart.getTotalItems());
                preparedStatement1.setDate(4, new Date(System.currentTimeMillis()));
                preparedStatement1.setInt(5,cart.getId());

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<LineItem> getLineItems(int cartid){
        List<LineItem> lineItems = new LinkedList<>();
        ProductDao productDao = new ProductDaoJDBC();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lineitems WHERE cart_id=?");
            preparedStatement.setInt(1,cartid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                LineItem item = new LineItem(productDao.find(resultSet.getInt("product_id")));
                lineItems.add(item);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lineItems;
    }
}