package persistence.service;

import persistence.dao.WineDao;
import persistence.model.Wine;
import java.util.List;

public class WineService {

    private static WineDao wineDao;

    public WineService() {
        wineDao = new WineDao();
    }

    public void persist(Wine wine) {
        wineDao.openCurrentSessionwithTransaction();
        wineDao.persist(wine);
        wineDao.closeCurrentSessionwithTransaction();
    }

    public void update(Wine wine) {
        wineDao.openCurrentSessionwithTransaction();
        wineDao.update(wine);
        wineDao.closeCurrentSessionwithTransaction();
    }

    public Wine findById(Long id) {
        wineDao.openCurrentSession();
        Wine wine = wineDao.findById(id);
        wineDao.closeCurrentSession();
        return wine;
    }

    public void delete(Long id) {
        wineDao.openCurrentSessionwithTransaction();
        Wine wine = wineDao.findById(id);
        wineDao.delete(wine);
        wineDao.closeCurrentSessionwithTransaction();
    }

    public List<Wine> findAll() {
        wineDao.openCurrentSession();
        List<Wine> wine = wineDao.findAll();
        wineDao.closeCurrentSession();
        return wine;
    }

    public WineDao wineDao() {
        return wineDao;
    }

    public Wine findByNameAndYear(String nameOfWine, String year) {
        wineDao().openCurrentSession();
        var wine = wineDao().findByNameAndYear(nameOfWine, year);
        wineDao().closeCurrentSession();
        return wine;
    }

    public List<Wine> findAllByYear(String year) {
        wineDao().openCurrentSession();
        var wine = wineDao.findAllByYear(year);
        wineDao().closeCurrentSession();
        return wine;
    }
}
