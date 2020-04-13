package persistence.service;


import persistence.dao.LocationDao;
import persistence.model.ContainerType;
import persistence.model.Location;

import java.util.List;

public class LocationService {

    private static LocationDao dao;

    public LocationService() {
        dao = new LocationDao();
    }

    public List<Location> findAll() {
        dao.openCurrentSession();
        List<Location> list = dao.findAll();
        dao.closeCurrentSession();
        return list;
    }

    public void update(Location location) {
        dao.openCurrentSessionwithTransaction();
        dao.update(location);
        dao.closeCurrentSessionwithTransaction();
    }

    public Location findByName(String name) {
        dao.openCurrentSession();
        Location model = dao.findByName(name);
        dao.closeCurrentSession();
        return model;
    }

    public void persist(Location location) {
        dao.openCurrentSessionwithTransaction();
        dao.persist(location);
        dao.closeCurrentSessionwithTransaction();
    }
}
