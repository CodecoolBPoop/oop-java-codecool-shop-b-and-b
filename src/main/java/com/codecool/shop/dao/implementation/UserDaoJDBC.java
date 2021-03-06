package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.hash.PasswordAuthentication;
import com.codecool.shop.model.User;

import java.sql.*;

public class UserDaoJDBC extends JdbcBase implements UserDao {

    private static UserDaoJDBC instance = new UserDaoJDBC();

    public static UserDao getInstance() {
        return instance;
    }

    @Override
    public User getUser(String email, String password){
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email=?");
            preparedStatement.setString(1, email);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                PasswordAuthentication passwordAuthentication = new PasswordAuthentication();
                String storedHashedPassword = result.getString("password");
                if (!passwordAuthentication.authenticate(password, storedHashedPassword)) return null;
                User user = new User(result.getString("email"),
                                    result.getString("password"),
                                    result.getString("first_name"),
                                    result.getString("last_name"),
                                    result.getString("phone_number"),
                                    result.getString("shipping_address"),
                                    result.getString("billing_address"));
                user.setId(result.getInt("id"));
                return user;
            }
            System.out.println(result);

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void add(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (" +
                    "password, " +
                    "first_name, " +
                    "last_name, " +
                    "email, " +
                    "phone_number, " +
                    "shipping_address, " +
                    "billing_address" +
                    ") VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getShippingAddress());
            preparedStatement.setString(7, user.getBillingAddress());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
