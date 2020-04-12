package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.Entry;


import persistence.model.Container;
import persistence.model.Measure;

public class ContainerDao extends AbstractDao<Container> {

    public ContainerDao() {
        super(Container.class);
    }

    public Container findByDesignation(String designation) {
        return (Container) getCurrentSession().createCriteria(Container.class)
                .add(Restrictions.eq("designation", designation))
                .uniqueResult();
    }
}


