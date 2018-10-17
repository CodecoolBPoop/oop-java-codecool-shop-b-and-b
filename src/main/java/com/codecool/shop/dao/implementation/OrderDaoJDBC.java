package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class OrderDaoJDBC implements JdbcBase {

    public Order getOrder(int userid){
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM order WHERE user_id=?");
            preparedStatement.setInt(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = new Order(resultSet.getInt("user_id"));
                order.setId(resultSet.getInt("id"));
                order.setItems(getLineItems(order.getId()));
                connection.close();
                return order;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveOrder(Order order) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM order WHERE id=?");
            preparedStatement.setInt(1,order.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (resultSet != null){
                   PreparedStatement preparedStatement2= connection.prepareStatement("DELETE FROM order WHERE id=?");
                   preparedStatement2.setInt(1, order.getId());
                   preparedStatement2.execute();
                }
                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO order (user_id, total_price, total_items, date,id) VALUES (?,?,?,?,?)");
                preparedStatement1.setInt(1,order.getUserid());
                preparedStatement1.setDouble(2,order.getTotalPrice());
                preparedStatement1.setInt(3,order.getTotalItems());
                preparedStatement1.setDate(4, new Date(System.currentTimeMillis()));
                preparedStatement1.setInt(5,order.getId());

            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<LineItem> getLineItems(int orderid){
        List<LineItem> lineItems = new LinkedList<>();
        ProductDao productDao = new ProductDaoJDBC();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM lineitems WHERE order_id=?");
            preparedStatement.setInt(1,orderid);
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
