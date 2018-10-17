package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJDBC implements ProductDao, JdbcBase {


    private static ProductDao instance = new ProductDaoJDBC();

    public static ProductDao getInstance() {
        return instance;
    }
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
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id=?");
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
                ProductCategory category = productCategoryDao.find(resultSet.getInt("category_id"));
                SupplierDao supplierDao = new SupplierDaoJDBC();
                Supplier supplier = supplierDao.find(resultSet.getInt("supplier_id"));
                Product product = new Product(resultSet.getString("name"),resultSet.getFloat("price"),resultSet.getString("currency"),resultSet.getString("description"),category,supplier);
                product.setId(resultSet.getInt("id"));
                return product;
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE id=?");
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
                ProductCategory category = productCategoryDao.find(resultSet.getInt("category_id"));
                SupplierDao supplierDao = new SupplierDaoJDBC();
                Supplier supplier = supplierDao.find(resultSet.getInt("supplier_id"));
                Product product = new Product(resultSet.getString("name"),resultSet.getFloat("price"),resultSet.getString("currency"),resultSet.getString("description"),category,supplier);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE supplier_id=?");
            preparedStatement.setInt(1,supplier.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
                ProductCategory category = productCategoryDao.find(resultSet.getInt("category_id"));
                SupplierDao supplierDao = new SupplierDaoJDBC();
                supplier = supplierDao.find(resultSet.getInt("supplier_id"));
                Product product = new Product(resultSet.getString("name"),resultSet.getFloat("price"),resultSet.getString("currency"),resultSet.getString("description"),category,supplier);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> products = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE category_id=?");
            preparedStatement.setInt(1,productCategory.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
                productCategory = productCategoryDao.find(resultSet.getInt("category_id"));
                SupplierDao supplierDao = new SupplierDaoJDBC();
                Supplier supplier = supplierDao.find(resultSet.getInt("supplier_id"));
                Product product = new Product(resultSet.getString("name"),resultSet.getFloat("price"),resultSet.getString("currency"),resultSet.getString("description"),productCategory,supplier);
                product.setId(resultSet.getInt("id"));
                products.add(product);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
