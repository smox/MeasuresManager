package persistence.dao;

import persistence.model.Entry;

import java.util.List;

public interface ICrudDao<T> {
    void persist(T entity);
    void update(T entity);
    void delete(T entity);
    T findById(Long id);
    List<T> findAll();
}
