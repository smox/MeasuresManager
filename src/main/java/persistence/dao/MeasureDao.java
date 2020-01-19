package persistence.dao;


import org.hibernate.criterion.Restrictions;
import persistence.model.Measure;

import java.util.List;

public class MeasureDao extends Dao implements ICrudDao<Measure> {

    @Override
    public void persist(Measure entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Measure entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Measure findById(Long id) {
        return (Measure)getCurrentSession().createCriteria(Measure.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    public Measure findByName(String name) {
        return (Measure)getCurrentSession().createCriteria(Measure.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }

    @Override
    public void delete(Measure entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Measure> findAll() {
        return (List<Measure>)getCurrentSession().createCriteria(Measure.class).list();
    }
}
