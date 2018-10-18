package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoJDBCTest {
    @Test
    public void testAddSupplier(){
        SupplierDao supplierDao = new SupplierDaoJDBC();
        ((SupplierDaoJDBC) supplierDao).setDatabaseForTest();
        Supplier supplier = new Supplier("Test Supplier", "test description");
        Supplier supplier2= new Supplier("Another Test Supplier", "another test description");
        supplierDao.add(supplier2);
        supplierDao.add(supplier);
    }

    @Test
    public void testFindSupplierById(){
        SupplierDao supplierDao = new SupplierDaoJDBC();
        ((SupplierDaoJDBC) supplierDao).setDatabaseForTest();
        System.out.println(supplierDao.find(2));
    }

    @Test
    public void testRemoveById(){
        SupplierDao supplierDao = new SupplierDaoJDBC();
        ((SupplierDaoJDBC) supplierDao).setDatabaseForTest();
        supplierDao.remove(2);
    }

    @Test
    public void tesGetAll(){
        SupplierDao supplierDao = new SupplierDaoJDBC();
        ((SupplierDaoJDBC) supplierDao).setDatabaseForTest();
        List<Supplier> suppliers = supplierDao.getAll();
        for (Supplier supplier: suppliers){
            System.out.println(supplier);
        }
    }
}