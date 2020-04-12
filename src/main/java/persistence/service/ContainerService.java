package persistence.service;

import persistence.dao.ContainerDao;
import persistence.model.Container;
import persistence.model.Entry;

import java.util.List;

public class ContainerService {

    private static ContainerDao dao;

    public ContainerService() {
        dao = new ContainerDao();
    }

    public List<Container> findAll() {
        dao.openCurrentSession();
        List<Container> list = dao.findAll();
        dao.closeCurrentSession();
        return list;
    }

    public Container findByDesignation(String designation) {
        dao.openCurrentSession();
        Container model = dao.findByDesignation(designation);
        dao.closeCurrentSession();
        return model;
    }

    public void update(Container container) {
        dao.openCurrentSessionwithTransaction();
        dao.update(container);
        dao.closeCurrentSessionwithTransaction();
    }
}
