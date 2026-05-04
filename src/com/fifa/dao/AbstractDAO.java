package com.fifa.dao;

import com.fifa.util.DatabaseManager;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements DAO<T> {
    protected Connection getConnection() throws SQLException {
        return DatabaseManager.getConnection();
    }
}
