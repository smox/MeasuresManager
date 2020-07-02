package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.ContainerType;
import persistence.model.Location;

public class LocationDao extends AbstractDao<Location> {

    public LocationDao() {
        super(Location.class);
    }

    public Location findByName(String name) {
        return (Location) getCurrentSession().createCriteria(Location.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}


