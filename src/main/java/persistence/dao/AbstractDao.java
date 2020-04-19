package persistence.dao;


import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

public class AbstractDao<T> extends Dao implements ICrudDao<T> {

    final Class<T> typeParameterClass;

    public AbstractDao(Class<T> typeParameterClass) {
        super();
        this.typeParameterClass = typeParameterClass;
    }

    @Override
    public void persist(T entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public T findById(Long id) {
        return (T)getCurrentSession().createCriteria(typeParameterClass)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createCriteria(typeParameterClass).list();
    }
}
