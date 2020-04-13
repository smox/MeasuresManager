package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.Container;
import persistence.model.ContainerType;

public class ContainerTypeDao extends AbstractDao<ContainerType> {

    public ContainerTypeDao() {
        super(ContainerType.class);
    }

    public ContainerType findByName(String name) {
        return (ContainerType) getCurrentSession().createCriteria(ContainerType.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}


