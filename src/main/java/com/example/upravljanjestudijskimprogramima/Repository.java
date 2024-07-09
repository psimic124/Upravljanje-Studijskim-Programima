package com.example.upravljanjestudijskimprogramima;

import java.util.List;

public interface Repository<T> {
    void add(T entity);
    List<T> getAll();
    T getById(String id);
    void update(T entity);
    void delete(String id);
    void save();
    void load();
}
