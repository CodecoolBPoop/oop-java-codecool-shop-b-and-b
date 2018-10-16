package com.codecool.shop.dao.implementation;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJDBCTest {
    @Test
    public void testAddingProductToDB(){
        ProductDaoJDBC productDaoJDBC = new ProductDaoJDBC();
        Supplier supplier = new Supplier("A", "A" );
        ProductCategory cat = new ProductCategory("nothing", "Nothing", "Test");
        Product product = new Product("Test",200,"USD","This is a test",cat,supplier);
        productDaoJDBC.add(product);
    }
}