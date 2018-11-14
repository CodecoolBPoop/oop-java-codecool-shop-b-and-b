package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Order;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderDaoJDBC extends JdbcBase {

    private static OrderDaoJDBC instance = new OrderDaoJDBC();

    public static OrderDaoJDBC getInstance() {
        return instance;
    }

    public List<Order> getOrders(int userid){
        try {
            List<Order> orders = new ArrayList<>();
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM orders WHERE user_id=?");
            preparedStatement.setInt(1,userid);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                List<LineItem> lineItems = getLineItems(resultSet.getInt("id"));
                Order order = new Order(lineItems,resultSet.getInt("total_price"));
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void saveOrder(Order currentOrder) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders (user_id, total_price, total_items) VALUES (?,?,?);");
            preparedStatement.setInt(1,currentOrder.getUserid());
            preparedStatement.setDouble(2,currentOrder.getTotalPrice());
            preparedStatement.setInt(3,currentOrder.getTotalItems());
            preparedStatement.execute();

            int orderId = 1;
            preparedStatement = connection.prepareStatement("SELECT MAX(id) AS maxId FROM orders WHERE user_id=?");
            preparedStatement.setInt(1,currentOrder.getUserid());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orderId = resultSet.getInt("maxId");
            }

            saveLineItems(currentOrder.getItems(), orderId);
            connection.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void saveLineItems(List<LineItem> items, int orderid){
        try {
            Connection connection = getConnection();
            for (LineItem lineItem: items){
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO lineitems(order_id, quantity, product_id) VALUES (?,?,?)");
                preparedStatement.setInt(1, orderid);
                preparedStatement.setInt(2,lineItem.getQuantity());
                preparedStatement.setInt(3,lineItem.getProductId());
                preparedStatement.execute();
            }
            connection.close();
        }catch (SQLException e){
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
                item.setQuantity(resultSet.getInt("quantity"));
                lineItems.add(item);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lineItems;
    }
}
