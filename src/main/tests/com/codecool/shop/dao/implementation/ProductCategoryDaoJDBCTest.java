package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoJDBCTest {
    @Test
    public void testAddCategory(){
        ProductCategoryDao productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        ((ProductCategoryDaoJDBC) productCategoryDaoJDBC).setDatabaseForTest();
        productCategoryDaoJDBC.add(new ProductCategory("TestCategory22","TestDepartment", "This is a test category2"));
        productCategoryDaoJDBC.add(new ProductCategory("testcategory33", "testdepartment", "Test category 3"));
    }

    @Test
    public void testFindCategoryById(){
        ProductCategoryDao productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        ((ProductCategoryDaoJDBC) productCategoryDaoJDBC).setDatabaseForTest();
        System.out.println(productCategoryDaoJDBC.find(5));
    }

    @Test
    public void testRemoveById(){
        ProductCategoryDao productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        ((ProductCategoryDaoJDBC) productCategoryDaoJDBC).setDatabaseForTest();
        productCategoryDaoJDBC.remove(5);
    }

    @Test
    public void testGetAll(){
        ProductCategoryDao productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        ((ProductCategoryDaoJDBC) productCategoryDaoJDBC).setDatabaseForTest();
        List<ProductCategory> categories = productCategoryDaoJDBC.getAll();
        for (ProductCategory category:categories){
            System.out.println(category);
        }
    }
}
