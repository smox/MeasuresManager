package persistence.dao;

import persistence.model.Entry;


public class EntryDao extends AbstractDao<Entry> {

    public EntryDao() {
        super(Entry.class);
    }
}
