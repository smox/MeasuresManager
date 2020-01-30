package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.Wine;

import java.util.List;

public class WineDao extends AbstractDao<Wine> {
    public WineDao() {
        super(Wine.class);
    }

    public Wine findByNameAndYear(String nameOfWine, String year) {
        return (Wine)getCurrentSession().createCriteria(Wine.class)
                .add(Restrictions.eq("name", nameOfWine))
                .add(Restrictions.eq("year", year))
                .uniqueResult();
    }

    public List<Wine> findAllByYear(String year) {
        return (List<Wine>)getCurrentSession().createCriteria(Wine.class)
                .add(Restrictions.eq("year", year))
                .list();
    }
}
