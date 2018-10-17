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
        productCategoryDaoJDBC.add(new ProductCategory("TestCategory22","TestDepartment", "This is a test category2"));
        productCategoryDaoJDBC.add(new ProductCategory("testcategory33", "testdepartment", "Test category 3"));
    }

    @Test
    public void testFindCategoryById(){
        ProductCategoryDaoJDBC productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        System.out.println(productCategoryDaoJDBC.find(5));
    }

    @Test
    public void testRemoveById(){
        ProductCategoryDaoJDBC productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        productCategoryDaoJDBC.remove(5);
    }

    @Test
    public void testGetAll(){
        ProductCategoryDaoJDBC productCategoryDaoJDBC = new ProductCategoryDaoJDBC();
        List<ProductCategory> categories = productCategoryDaoJDBC.getAll();
        for (ProductCategory category:categories){
            System.out.println(category);
        }
    }
}
