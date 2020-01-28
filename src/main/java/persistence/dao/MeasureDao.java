package persistence.dao;


import org.hibernate.criterion.Restrictions;
import persistence.model.Measure;

import java.util.List;


public class MeasureDao extends AbstractDao<Measure> {

    public MeasureDao() {
        super(Measure.class);
    }

    public Measure findByName(String name) {
        return (Measure)getCurrentSession().createCriteria(Measure.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}
