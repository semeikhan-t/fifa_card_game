package com.fifa.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/fifa_card_game";
    private static final String USER = "postgres";
    private static final String PASSWORD = "helloworld"; 

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("org.postgresql.Driver");
                try {
                    connection = DriverManager.getConnection(URL, USER, PASSWORD);
                } catch (SQLException e) {
                    
                    if (e.getSQLState().equals("3D000")) { 
                        checkAndCreateDatabase();
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    } else {
                        throw e;
                    }
                }
            } catch (ClassNotFoundException e) {
                System.err.println("PostgreSQL Driver not found!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static void checkAndCreateDatabase() {
        String baseUri = "jdbc:postgresql://localhost:5432/postgres";
        try (Connection conn = DriverManager.getConnection(baseUri, USER, PASSWORD);
             java.sql.Statement stmt = conn.createStatement()) {
            
            
            java.sql.ResultSet rs = stmt.executeQuery("SELECT 1 FROM pg_database WHERE datname = 'fifa_card_game'");
            if (!rs.next()) {
                System.out.println(" База данных не найдена. Создаю 'fifa_card_game'...");
                stmt.executeUpdate("CREATE DATABASE fifa_card_game");
                System.out.println(" База данных создана.");
            }
        } catch (SQLException e) {
            System.err.println(" Ошибка при создании базы данных: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}