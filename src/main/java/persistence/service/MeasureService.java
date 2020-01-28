package persistence.service;

import persistence.dao.MeasureDao;
import persistence.model.Entry;
import persistence.model.Measure;

import java.util.List;


public class MeasureService {

    private static MeasureDao measureDao;

    public MeasureService() {
        measureDao = new MeasureDao();
    }

    public void persist(Measure entity) {
        measureDao.openCurrentSessionwithTransaction();
        measureDao.persist(entity);
        measureDao.closeCurrentSessionwithTransaction();
    }

    public void update(Measure entity) {
        measureDao.openCurrentSessionwithTransaction();
        measureDao.update(entity);
        measureDao.closeCurrentSessionwithTransaction();
    }

    public Measure findById(Long id) {
        measureDao.openCurrentSession();
        Measure measure = measureDao.findById(id);
        measureDao.closeCurrentSession();
        return measure;
    }
    public Measure findByName(String name) {
        measureDao.openCurrentSession();
        Measure measure = measureDao.findByName(name);
        measureDao.closeCurrentSession();
        return measure;
    }

    public void delete(Long id) {
        measureDao.openCurrentSessionwithTransaction();
        Measure measure = measureDao.findById(id);
        measureDao.delete(measure);
        measureDao.closeCurrentSessionwithTransaction();
    }

    public List<Measure> findAll() {
        measureDao.openCurrentSession();
        List<Measure> measures = measureDao.findAll();
        measureDao.closeCurrentSession();
        return measures;
    }

    public MeasureDao entryDao() {
        return measureDao;
    }

}
