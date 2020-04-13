package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.Entry;


import persistence.model.Container;
import persistence.model.Measure;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ContainerDao extends AbstractDao<Container> {

    public ContainerDao() {
        super(Container.class);
    }

    public Container findByDesignation(String designation) {
        return (Container) getCurrentSession().createCriteria(Container.class)
                .add(Restrictions.eq("designation", designation))
                .add(Restrictions.isNull("deletedAt"))
                .uniqueResult();
    }

    @Override
    public List<Container> findAll() {
        return (List<Container>) getCurrentSession().createCriteria(Container.class)
                .add(Restrictions.isNull("deletedAt"))
                .list();
    }

    @Override
    public void delete(Container entity) {
        Container container = findById(entity.getId());
        if(container != null) {
            container.setDeletedAt(new Date());
            persist(container);
        } else {
            throw new RuntimeException("Container cannot be deleted, because it doesn't exist");
        }
    }

    @Override
    public Container findById(Long id) {
        return (Container) getCurrentSession().createCriteria(Container.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.isNull("deletedAt"))
                .uniqueResult();
    }
}


