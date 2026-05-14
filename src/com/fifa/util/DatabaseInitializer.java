package com.fifa.util;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInitializer {

    public void initializeDatabase() {
        try (Connection conn = DatabaseManager.getConnection()) {
            System.out.println(" Начинаю инициализацию базы данных...");
            
            executeSqlScript(conn, "db/init.sql");
            
            executeSqlScript(conn, "db/seed.sql");
            
            System.out.println(" База данных успешно обновлена!");
        } catch (SQLException e) {
            System.err.println(" Ошибка при работе с БД: " + e.getMessage());
        }
    }

    private void executeSqlScript(Connection conn, String resourcePath) {
        String sql = readSqlFile(resourcePath);
        if (sql == null || sql.trim().isEmpty()) {
            System.err.println(" Файл не найден или пуст: " + resourcePath);
            return;
        }

        try (Statement stmt = conn.createStatement()) {
            
            String cleanSql = sql.replaceAll("--.*", "");
            String[] statements = cleanSql.split(";");
            
            int count = 0;
            for (String s : statements) {
                String trimmed = s.trim();
                if (!trimmed.isEmpty()) {
                    try {
                        stmt.execute(trimmed);
                        count++;
                    } catch (SQLException e) {
                        
                        if (!trimmed.toUpperCase().startsWith("DELETE")) {
                            System.err.println("  Ошибка в команде SQL: " + e.getMessage());
                        }
                    }
                }
            }
            System.out.println("    Выполнено " + count + " команд из " + resourcePath);
        } catch (SQLException e) {
            System.err.println(" Ошибка выполнения скрипта " + resourcePath + ": " + e.getMessage());
        }
    }

    private String readSqlFile(String resourcePath) {
        String[] paths = {
            "src/main/resources/" + resourcePath,
            "resources/" + resourcePath
        };

        for (String p : paths) {
            try {
                Path path = Paths.get(p);
                if (Files.exists(path)) {
                    return Files.readString(path);
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

    public static void initialize() {
        new DatabaseInitializer().initializeDatabase();
    }

    public static void runSeed() {
        new DatabaseInitializer().initializeDatabase();
    }
}