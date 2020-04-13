package persistence.service;

import persistence.dao.ContainerDao;
import persistence.dao.ContainerTypeDao;
import persistence.model.Container;
import persistence.model.ContainerType;

import java.util.List;

public class ContainerTypeService {

    private static ContainerTypeDao dao;

    public ContainerTypeService() {
        dao = new ContainerTypeDao();
    }

    public List<ContainerType> findAll() {
        dao.openCurrentSession();
        List<ContainerType> list = dao.findAll();
        dao.closeCurrentSession();
        return list;
    }

    public void update(ContainerType containerType) {
        dao.openCurrentSessionwithTransaction();
        dao.update(containerType);
        dao.closeCurrentSessionwithTransaction();
    }

    public ContainerType findByName(String name) {
        dao.openCurrentSession();
        ContainerType model = dao.findByName(name);
        dao.closeCurrentSession();
        return model;
    }

    public void persist(ContainerType containerType) {
        dao.openCurrentSessionwithTransaction();
        dao.persist(containerType);
        dao.closeCurrentSessionwithTransaction();
    }
}
