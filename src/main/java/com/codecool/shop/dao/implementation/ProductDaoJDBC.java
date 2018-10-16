package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductDaoJDBC implements ProductDao, JdbcBase {

    @Override
    public void add(Product product) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (category_id,supplier_id,price,currency,name,description) VALUES (?,?,?,?,?,?);");
            preparedStatement.setInt(1,product.getProductCategory().getId());
            preparedStatement.setInt(2,product.getSupplier().getId());
            preparedStatement.setDouble(3,product.getDefaultPrice());
            preparedStatement.setString(4,product.getDefaultCurrency().toString());
            preparedStatement.setString(5,product.getName());
            preparedStatement.setString(6,product.getDescription());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }
}
