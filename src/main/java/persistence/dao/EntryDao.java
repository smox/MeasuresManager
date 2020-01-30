package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.Entry;
import persistence.model.Measure;
import persistence.model.Wine;

import java.util.List;


public class EntryDao extends AbstractDao<Entry> {

    public EntryDao() {
        super(Entry.class);
    }


    public List<Entry> findAllByWineAndYear(Wine wine) {
        return (List<Entry>)getCurrentSession().createCriteria(Entry.class)
                .add(Restrictions.eq("wine", wine))
                .list();
    }
}
