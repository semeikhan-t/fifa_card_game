package com.fifa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    // Default postgres credentials as confirmed by user "Yes i have local postgres instance"
    private static final String URL = "jdbc:postgresql://localhost:5432/fifa26";
    private static final String USER = "postgres";
    private static final String PASSWORD = "helloworld"; // Commonly used defaults

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
