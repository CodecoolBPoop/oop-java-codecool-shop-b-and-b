package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoJDBCTest {
    @Test
    public void testAddingProductToDB(){
        ProductDao productDaoJDBC = new ProductDaoJDBC();
        SupplierDao supplierDao = new SupplierDaoJDBC();
        Supplier supplier = supplierDao.find(3);
        ProductCategoryDao productCategoryDao = new ProductCategoryDaoJDBC();
        ProductCategory cat = productCategoryDao.find(8);
        Product product = new Product("Test",200,"USD","This is a test",cat,supplier);
        productDaoJDBC.add(product);
    }

    @Test
    public void testFindProductById(){
        ProductDao productDao = new ProductDaoJDBC();
        Product product = productDao.find(2);
        System.out.println(product);
    }

    @Test
    public void testGetAll(){
        ProductDao productDao = new ProductDaoJDBC();
        List<Product> products = productDao.getAll();
        for (Product product: products){
            System.out.println(product);
        }
    }
}