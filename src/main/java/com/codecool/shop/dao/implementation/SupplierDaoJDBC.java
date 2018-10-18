package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoJDBC extends JdbcBase implements SupplierDao {

    private static SupplierDao instance = new SupplierDaoJDBC();

    public static SupplierDao getInstance() {
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO suppliers (name, description) VALUES (?,?)");
            preparedStatement.setString(1,supplier.getName());
            preparedStatement.setString(2, supplier.getDescription());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Supplier find(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM suppliers WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Supplier supplier = new Supplier(resultSet.getString("name"), resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                connection.close();
                return supplier;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void remove(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM suppliers WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Supplier> getAll() {
        List<Supplier> categories = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM suppliers");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getString("name"),rs.getString("description"));
                supplier.setId(rs.getInt("id"));
                categories.add(supplier);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
