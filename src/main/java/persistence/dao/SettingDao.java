package persistence.dao;

import org.hibernate.criterion.Restrictions;
import persistence.model.Setting;

public class SettingDao extends AbstractDao<Setting> {
    public SettingDao() {
        super(Setting.class);
    }

    public Setting findCurrent() {
        return (Setting) getCurrentSession().createCriteria(Setting.class)
                .add(Restrictions.isNull("deletedAt"))
                .uniqueResult();
    }
}
