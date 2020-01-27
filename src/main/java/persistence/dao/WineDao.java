package persistence.dao;

import persistence.model.Wine;

public class WineDao extends AbstractDao<Wine> {
    public WineDao() {
        super(Wine.class);
    }
}
