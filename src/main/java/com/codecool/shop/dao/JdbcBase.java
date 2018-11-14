package com.codecool.shop.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcBase {
    String DATABASE = "jdbc:postgresql://localhost:5432/codecoolshop";
    String DB_USER = "postgres";
    String DB_PASSWORD = "q8h1j5b321";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

    public void setDatabaseForTest(){
        DATABASE = "jdbc:postgresql://localhost:5432/shoptest";
    }
}
