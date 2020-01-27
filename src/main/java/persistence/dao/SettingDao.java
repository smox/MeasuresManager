package persistence.dao;


import org.hibernate.criterion.Restrictions;
import persistence.model.Setting;

import java.util.List;

public class SettingDao extends Dao implements ICrudDao<Setting> {

    @Override
    public void persist(Setting setting) {
        getCurrentSession().save(setting);
    }

    @Override
    public void update(Setting setting) {
        getCurrentSession().update(setting);
    }

    @Override
    public Setting findById(Long id) {
        return (Setting) getCurrentSession().createCriteria(Setting.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
    }

    @Override
    public void delete(Setting setting) {
        getCurrentSession().delete(setting);
    }

    @Override
    public List<Setting> findAll() {
        return (List<Setting>)getCurrentSession().createCriteria(Setting.class).list();
    }
}
