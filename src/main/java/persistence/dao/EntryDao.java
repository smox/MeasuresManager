package persistence.dao;


import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import persistence.model.Entry;

import javax.persistence.Id;
import java.util.List;

public class EntryDao extends Dao implements ICrudDao<Entry> {

    @Override
    public void persist(Entry entity) {
        getCurrentSession().save(entity);
    }

    @Override
    public void update(Entry entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public Entry findById(Long id) {
        return (Entry)getCurrentSession().createCriteria(Entry.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void delete(Entry entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public List<Entry> findAll() {
        return (List<Entry>)getCurrentSession().createCriteria(Entry.class).list();
    }
}
