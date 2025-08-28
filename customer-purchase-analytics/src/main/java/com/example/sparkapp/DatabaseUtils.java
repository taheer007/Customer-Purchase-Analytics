package com.example.sparkapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtils {
    private static final String URL = "jdbc:mysql://localhost:3306/customerdb";
    private static final String USER = "root";
    private static final String PASSWORD = "Taheer6364@#";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
