package com.fifa.dao;

import java.util.List;

public interface DAO<T> {
    T findById(int id);
    List<T> findAll();
    void save(T entity);
    void delete(int id);
}
