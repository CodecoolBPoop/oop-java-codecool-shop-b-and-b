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

    // TODO
    public void saveCart(Order cart) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carts WHERE id=?");
            preparedStatement.setInt(1,cart.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                // Delete existing cart
                PreparedStatement preparedStatement1 = connection.prepareStatement("DELETE FROM carts WHERE id=?");
                preparedStatement1.setInt(1, cart.getId());
                preparedStatement1.execute();

                // Save cart into db
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO carts (user_id) VALUES (?)");
                preparedStatement2.setInt(1,cart.getUserid());
                preparedStatement2.execute();
                // Get the carts db generated id
                PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT id FROM carts WHERE user_id=?");
                preparedStatement3.setInt(1, cart.getId());
                ResultSet resultSet1 = preparedStatement3.executeQuery();
                int cartId = resultSet1.getInt("id");
                // save line items into db


            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    // TODO
    public void saveLineItems(List<LineItem> lineItems, int cartId) {
        try {
            Connection connection = getConnection();
            for (LineItem lineItem: lineItems) {
                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO lineitems (cart_id, quantity, product_id) VALUES (?, ?, ?)");
                preparedStatement1.setInt(1,cartId);
                preparedStatement1.setInt(2,lineItem.getQuantity());
                preparedStatement1.setString(3,lineItem.ge);

            }
        } catch (SQLException e) {
            System.out.println(e);
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