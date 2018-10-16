package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.JdbcBase;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierDaoJDBC implements SupplierDao, JdbcBase {

    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
