package com.example.carsagency.database;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> findById(int id);
    List<T> findAll();
    boolean save(T record);
    boolean update(T record);
    boolean delete (int id);
}
