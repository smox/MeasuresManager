package persistence.service;

import persistence.dao.EntryDao;
import persistence.model.Entry;

import java.util.List;


public class EntryService {

    private static EntryDao entryDao;

    public EntryService() {
        entryDao = new EntryDao();
    }

    public void persist(Entry entity) {
        System.out.println("Try opening a transaction....");
        entryDao.openCurrentSessionwithTransaction();
        System.out.println("Transaction started....");
        System.out.println("Starting persisting....");
        entryDao.persist(entity);
        System.out.println("Entity persisted....");
        entryDao.closeCurrentSessionwithTransaction();
        System.out.println("Transaction closed....");
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
        Entry book = entryDao.findById(id);
        entryDao.delete(book);
        entryDao.closeCurrentSessionwithTransaction();
    }

    public List<Entry> findAll() {
        entryDao.openCurrentSession();
        List<Entry> books = entryDao.findAll();
        entryDao.closeCurrentSession();
        return books;
    }

    public EntryDao entryDao() {
        return entryDao;
    }
}
