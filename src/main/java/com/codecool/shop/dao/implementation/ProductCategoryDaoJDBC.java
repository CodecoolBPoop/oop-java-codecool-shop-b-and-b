package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoJDBC implements ProductCategoryDao, JdbcBase {

    @Override
    public void add(ProductCategory category) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories (name, description, department) VALUES (?,?,?)");
            preparedStatement.setString(1,category.getName());
            preparedStatement.setString(2,category.getDescription());
            preparedStatement.setString(3,category.getDepartment());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int categoryid = result.getInt("id");
                ProductCategory productCategory = new ProductCategory(result.getString("name"),result.getString("department"), result.getString("description"));
                productCategory.setId(categoryid);
                return  productCategory;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public void remove(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categories WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> categories = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ProductCategory category = new ProductCategory(rs.getString("name"),rs.getString("department"),rs.getString("description"));
                category.setId(rs.getInt("id"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
