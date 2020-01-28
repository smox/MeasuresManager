package persistence.service;

import org.hibernate.Session;
import persistence.dao.EntryDao;
import persistence.model.Entry;
import persistence.model.Measure;
import persistence.model.Wine;

import java.util.List;


public class EntryService {

    private static EntryDao entryDao;

    public EntryService() {
        entryDao = new EntryDao();
    }

    public void persist(Entry entity) {
        entryDao.openCurrentSessionwithTransaction();
        entryDao.persist(entity);
        entryDao.closeCurrentSessionwithTransaction();
    }

    public void update(Entry entity) {
        entryDao.openCurrentSessionwithTransaction();
        entryDao.update(entity);
        entryDao.closeCurrentSessionwithTransaction();
    }

    public Entry findById(Long id) {
        entryDao.openCurrentSession();
        Entry entry = entryDao.findById(id);
        entryDao.closeCurrentSession();
        return entry;
    }

    public void delete(Long id) {
        entryDao.openCurrentSessionwithTransaction();
        Entry entry = entryDao.findById(id);
        entryDao.delete(entry);
        entryDao.closeCurrentSessionwithTransaction();
    }

    public List<Entry> findAll() {
        entryDao.openCurrentSession();
        List<Entry> entries = entryDao.findAll();
        entryDao.closeCurrentSession();
        return entries;
    }

    public List<Entry> findAllByWineAndYear(Wine wine, String year) {
        entryDao().openCurrentSession();
        List<Entry> entries = entryDao().findAllByWineAndYear(wine, year);
        entryDao().closeCurrentSession();
        return entries;
    }

    public EntryDao entryDao() {
        return entryDao;
    }
}
