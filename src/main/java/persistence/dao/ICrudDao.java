package persistence.dao;

import java.util.List;

public interface ICrudDao<T> {
    void persist(T entity);
    void update(T entity);
    T findById(Long id);
    void delete(T entity);
    List<T> findAll();
}
