package persistence.service;

import persistence.dao.ContainerAmountMapDao;
import persistence.model.ContainerAmountMap;

import java.util.List;


public class ContainerAmountMapService {

    private static ContainerAmountMapDao containerAmountMapDao;

    public ContainerAmountMapService() {
        containerAmountMapDao = new ContainerAmountMapDao();
    }

    public void persist(ContainerAmountMap entity) {
        containerAmountMapDao.openCurrentSessionwithTransaction();
        containerAmountMapDao.persist(entity);
        containerAmountMapDao.closeCurrentSessionwithTransaction();
    }

    public void update(ContainerAmountMap entity) {
        containerAmountMapDao.openCurrentSessionwithTransaction();
        containerAmountMapDao.update(entity);
        containerAmountMapDao.closeCurrentSessionwithTransaction();
    }

    public ContainerAmountMap findById(String container) {
        containerAmountMapDao.openCurrentSession();
        ContainerAmountMap containerAmountMap = containerAmountMapDao.findById(container);
        containerAmountMapDao.closeCurrentSession();
        return containerAmountMap;
    }

    public void delete(Long id) {
        containerAmountMapDao.openCurrentSessionwithTransaction();
        ContainerAmountMap containerAmountMap = containerAmountMapDao.findById(id);
        containerAmountMapDao.delete(containerAmountMap);
        containerAmountMapDao.closeCurrentSessionwithTransaction();
    }

    public List<ContainerAmountMap> findAll() {
        containerAmountMapDao.openCurrentSession();
        List<ContainerAmountMap> containerAmountMaps = containerAmountMapDao.findAll();
        containerAmountMapDao.closeCurrentSession();
        return containerAmountMaps;
    }

    public ContainerAmountMapDao containerAmountMapDao() {
        return containerAmountMapDao;
    }
}
